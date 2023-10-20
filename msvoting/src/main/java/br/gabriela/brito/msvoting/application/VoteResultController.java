package br.gabriela.brito.msvoting.application;

import br.gabriela.brito.msvoting.application.representation.VoteResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("api/v1/vote-results")
@RequiredArgsConstructor

public class VoteResultController {

    private final VoteResultService voteResultService;


    @GetMapping
    public VoteResultResponse getVoteResult(@RequestParam Long idSessao) {
        return voteResultService.getVoteResult(idSessao);
    }
}