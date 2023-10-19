package br.gabriela.brito.msvoting.application;

import br.gabriela.brito.msvoting.application.ex.VotingException;
import br.gabriela.brito.msvoting.application.representation.VoteRequest;
import br.gabriela.brito.msvoting.application.representation.VoteResponse;
import br.gabriela.brito.msvoting.application.representation.VotingSessionResponse;
import br.gabriela.brito.msvoting.domain.model.Vote;
import br.gabriela.brito.msvoting.infra.clients.EmployeesControllerClient;
import br.gabriela.brito.msvoting.infra.clients.infra.repository.VoteRepository;
import br.gabriela.brito.msvoting.infra.clients.infra.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VotingSessionService votingSessionService;
    private final EmployeesControllerClient employeesClient;
    private final VoteRepository voteRepository;
    private final VotingSessionRepository votingRepository;

    public VoteResponse vote(VoteRequest voteRequest) {

        VotingSessionResponse sessionResponse = votingSessionService.getVotingSession(voteRequest.getSessionId());

        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(sessionResponse.getTempoSessao())) {
            throw new VotingException("Sessão inválida ou tempo esgotado.");
        }

        if (voteRepository.existsBySessionIdAndCpfFuncionario(voteRequest.getSessionId(), voteRequest.getCpfFuncionario())) {
            throw new VotingException("Funcionário já votou nesta sessão.");
        }

        validateVote(voteRequest.getVoto());

        Vote vote = new Vote();
        vote.setSessionId(voteRequest.getSessionId());
        vote.setCpfFuncionario(voteRequest.getCpfFuncionario());
        vote.setVoto(voteRequest.getVoto());

        Vote savedVote = voteRepository.save(vote);

        return buildVoteResponse(savedVote.getId());
    }

    private void validateVote(String voto) {
        if (!"Aprovar".equalsIgnoreCase(voto) && !"Rejeitar".equalsIgnoreCase(voto)) {
            throw new IllegalArgumentException("O tipo de voto deve ser 'Aprovar' ou 'Rejeitar'.");
        }
    }

    private VoteResponse buildVoteResponse(Long voteId) {
        return new VoteResponse(voteId);
    }
}
