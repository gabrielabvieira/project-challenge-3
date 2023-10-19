package br.gabriela.brito.msvoting.infra.clients;

import br.gabriela.brito.msvoting.application.representation.ProposalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "msproposals", path = "/api/v1/proposals")
public interface ProposalsControllerClient {

    @GetMapping(params = "id")
    ResponseEntity<ProposalResponse> getDataByProposal(@RequestParam("id") Long id);

    @PostMapping
    ResponseEntity<Long> createProposal(@RequestBody ProposalResponse proposalRequest);
}