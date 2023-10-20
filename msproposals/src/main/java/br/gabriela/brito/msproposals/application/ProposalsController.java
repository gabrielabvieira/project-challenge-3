package br.gabriela.brito.msproposals.application;

import br.gabriela.brito.msproposals.application.representation.ProposalSaveRequest;
import br.gabriela.brito.msproposals.application.representation.ProposalsByEmployeeResponse;
import br.gabriela.brito.msproposals.domain.Proposal;
import br.gabriela.brito.msproposals.application.ex.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/proposals")
@RequiredArgsConstructor
public class ProposalsController {

    private final ProposalService proposalService;
    private final EmployeeProposalService employeeProposalService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createProposal(@RequestBody ProposalSaveRequest request) {
        try {
            Long proposalId = proposalService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("id_proposta", proposalId.toString()));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "CPF de funcionário não encontrado."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erro ao processar a proposta."));
        }
    }

    @GetMapping(params = "id")
    public ResponseEntity<Proposal> getDataByProposal(@RequestParam("id") Long id) {
        Optional<Proposal> proposal = proposalService.getDataByProposal(id);

        return proposal.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ProposalsByEmployeeResponse>> getProposalsByEmployee(
            @RequestParam("cpf") String cpf) {
        try {
            List<ProposalsByEmployeeResponse> resultList = employeeProposalService.listProposalsByCpf(cpf).stream()
                    .map(ProposalsByEmployeeResponse::fromModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(resultList);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(List.of());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(List.of());
        }
    }
}
