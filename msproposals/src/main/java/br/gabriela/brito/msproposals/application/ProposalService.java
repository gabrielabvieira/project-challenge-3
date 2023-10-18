package br.gabriela.brito.msproposals.application;

import br.gabriela.brito.msproposals.application.ex.ApiException;
import br.gabriela.brito.msproposals.application.ex.NotFoundException;
import br.gabriela.brito.msproposals.application.ex.UnauthorizedException;
import br.gabriela.brito.msproposals.application.representation.ProposalSaveRequest;
import br.gabriela.brito.msproposals.domain.Proposal;
import br.gabriela.brito.msproposals.infra.clients.EmployeesControllerClient;
import br.gabriela.brito.msproposals.infra.repository.ProposalRepository;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProposalService {

    private final ProposalRepository repository;
    private final EmployeesControllerClient employeesClient;

    public ProposalService(ProposalRepository repository, EmployeesControllerClient employeesClient) {
        this.repository = repository;
        this.employeesClient = employeesClient;
    }

    @Transactional
    public Long save(ProposalSaveRequest proposalSaveRequest) {
        try {
            employeesClient.validateEmployee(proposalSaveRequest.getCpf());

            Proposal proposal = Proposal.builder()
                    .nome(proposalSaveRequest.getNome())
                    .descricao(proposalSaveRequest.getDescricao())
                    .cpf(proposalSaveRequest.getCpf())
                    .dataCriacao(LocalDateTime.now())
                    .build();

            Proposal savedProposal = repository.save(proposal);
            return savedProposal.getId();
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("CPF de funcionário não encontrado.");
        } catch (FeignException e) {
            throw new UnauthorizedException("CPF de funcionário inválido.");
        } catch (Exception e) {
            throw new ApiException("Erro ao processar a proposta.");
        }
    }

    public Optional<Proposal> getDataByProposal(Long id) {
        return repository.findById(id);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<Proposal>> getProposalsByEmployee(@RequestParam("cpf") String cpf) {
        try {
            List<Proposal> proposals = repository.findByCpf(cpf);

            if (proposals.isEmpty()) {
                log.info("Proposals not found for CPF: {}", cpf);
                return ResponseEntity.notFound().build();
            }

            log.info("Proposals found for CPF: {}", cpf);
            return ResponseEntity.ok(proposals);
        } catch (FeignException.NotFound e) {
            log.error("Feign exception: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (FeignException e) {
            log.error("Feign exception: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
