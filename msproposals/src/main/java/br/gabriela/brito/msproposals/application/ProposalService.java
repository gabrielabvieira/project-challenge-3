package br.gabriela.brito.msproposals.application;

import br.gabriela.brito.msproposals.application.ex.ApiException;
import br.gabriela.brito.msproposals.application.ex.NotFoundException;
import br.gabriela.brito.msproposals.application.ex.UnauthorizedException;
import br.gabriela.brito.msproposals.application.representation.ProposalSaveRequest;
import br.gabriela.brito.msproposals.domain.Proposal;
import br.gabriela.brito.msproposals.infra.clients.EmployeesControllerClient;
import br.gabriela.brito.msproposals.infra.repository.ProposalRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository repository;
    private final EmployeesControllerClient employeesClient;

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
}
