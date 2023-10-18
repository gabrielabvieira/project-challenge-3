package br.gabriela.brito.msvoting.infra.clients;

import br.gabriela.brito.msvoting.application.representation.ProposalRequest;
import br.gabriela.brito.msvoting.application.representation.VotingSessionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "msproposals", path = "/api/v1/proposals")
public interface ProposalsControllerClient {

    @GetMapping(params = "id")
    public ResponseEntity<ProposalRequest> getDataByProposal(@RequestParam("id") Long id);

    @PostMapping
    ResponseEntity<Long> createProposal(@RequestBody ProposalRequest proposalRequest);
}