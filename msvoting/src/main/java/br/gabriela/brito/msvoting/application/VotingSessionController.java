package br.gabriela.brito.msvoting.application;

import br.gabriela.brito.msvoting.application.representation.ProposalResponse;
import br.gabriela.brito.msvoting.application.representation.VotingSessionResponse;
import br.gabriela.brito.msvoting.application.representation.VotingSessionSaveRequest;
import br.gabriela.brito.msvoting.infra.clients.ProposalsControllerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/voting-session")


public class VotingSessionController {

    private final VotingSessionService votingSessionService;
    private final ProposalsControllerClient proposalsClient;

    public VotingSessionController(VotingSessionService votingSessionService, ProposalsControllerClient proposalsClient) {
        this.votingSessionService = votingSessionService;
        this.proposalsClient = proposalsClient;
    }

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity<VotingSessionResponse> createVotingSession(@RequestBody VotingSessionSaveRequest request) {
        VotingSessionResponse votingSessionResponse = votingSessionService.createVotingSession(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(votingSessionResponse);
    }

    @GetMapping(params = "id")
    public ResponseEntity<VotingSessionResponse> getDataBySession(@RequestParam("id") Long id) {
        VotingSessionResponse response = votingSessionService.getVotingSession(id);

        ResponseEntity<ProposalResponse> proposalResponseEntity = proposalsClient.getDataByProposal(response.getIdProposta());
        ProposalResponse proposalResponse = proposalResponseEntity.getBody();

        response.setNomeProposta(proposalResponse.getNome());
        response.setDescricao(proposalResponse.getDescricao());

        return ResponseEntity.ok(response);
    }
}


