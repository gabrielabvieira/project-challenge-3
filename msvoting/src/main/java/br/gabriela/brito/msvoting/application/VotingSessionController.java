package br.gabriela.brito.msvoting.application;

import br.gabriela.brito.msvoting.application.representation.VotingSessionResponse;
import br.gabriela.brito.msvoting.application.representation.VotingSessionSaveRequest;
import br.gabriela.brito.msvoting.domain.model.VotingSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/voting-session")
@RequiredArgsConstructor
public class VotingSessionController {

    private final VotingSessionService votingSessionService;

    @GetMapping
    public String status(){
        return "ok";
    }
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createVotingSession(@RequestBody VotingSessionSaveRequest request){
        VotingSession votingSession = votingSessionService.createVotingSession(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id_sessao", votingSession.getId().toString()));
    }

    @GetMapping(params = "id")
    public ResponseEntity<VotingSessionResponse> getDataBySession(@RequestParam("id") Long id) {
        VotingSessionResponse response = new VotingSessionResponse();
        return ResponseEntity.ok(response);
    }
}

