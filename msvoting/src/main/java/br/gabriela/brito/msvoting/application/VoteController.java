package br.gabriela.brito.msvoting.application;

import br.gabriela.brito.msvoting.application.ex.VotingException;
import br.gabriela.brito.msvoting.application.representation.VoteRequest;
import br.gabriela.brito.msvoting.application.representation.VoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Object> vote(@RequestBody VoteRequest voteRequest) {
        try {
            VoteResponse voteResponse = voteService.vote(voteRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("id_voto", voteResponse.getIdVoto(), "status", voteResponse.getStatus()));
        } catch (VotingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

