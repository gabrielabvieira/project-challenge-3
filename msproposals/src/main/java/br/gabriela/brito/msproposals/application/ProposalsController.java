package br.gabriela.brito.msproposals.application;

import br.gabriela.brito.msproposals.application.representation.ProposalSaveRequest;
import br.gabriela.brito.msproposals.application.representation.ProposalsByEmployeeResponse;
import br.gabriela.brito.msproposals.domain.EmployeeProposal;
import br.gabriela.brito.msproposals.domain.Proposal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("api/v1/proposals")
@RequiredArgsConstructor
public class ProposalsController {

    private final ProposalService proposalService;
    private final EmployeeProposalService employeeProposalService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity<String> createProposal(@RequestBody ProposalSaveRequest request){
        Proposal proposal = request.toModel();
        proposalService.save(proposal);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "id")
    public ResponseEntity<Proposal> getDataByProposal(@RequestParam("id")Long id) {
        Optional<Proposal> proposal = proposalService.getDataByProposal(id);

        return proposal.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
        }


    @GetMapping(params = "cpf")
    public ResponseEntity<List<ProposalsByEmployeeResponse>> getProposalsByEmployee(
            @RequestParam("cpf") String cpf){
        List<EmployeeProposal> lista = employeeProposalService.listProposalsByCpf(cpf);
        List<ProposalsByEmployeeResponse> resultList = lista.stream()
                .map(ProposalsByEmployeeResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);

    }

}
