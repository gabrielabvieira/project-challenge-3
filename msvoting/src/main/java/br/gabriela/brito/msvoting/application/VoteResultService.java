package br.gabriela.brito.msvoting.application;

import br.gabriela.brito.msvoting.application.representation.VoteResultResponse;
import br.gabriela.brito.msvoting.domain.model.Vote;
import br.gabriela.brito.msvoting.domain.model.VoteResult;
import br.gabriela.brito.msvoting.infra.clients.infra.repository.VoteRepository;
import br.gabriela.brito.msvoting.infra.clients.infra.repository.VoteResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteResultService {
    private final VoteResultRepository voteResultRepository;
    private final VoteRepository voteRepository;

    public VoteResultResponse getVoteResult(Long sessionId) {
        List<Vote> votes = voteRepository.findBySessionId(sessionId);

        int approvedCount = 0;
        int rejectedCount = 0;

        for (Vote vote : votes) {
            if ("Aprovar".equalsIgnoreCase(vote.getVoto())) {
                approvedCount++;
            } else if ("Rejeitar".equalsIgnoreCase(vote.getVoto())) {
                rejectedCount++;
            }
        }

        if (approvedCount > rejectedCount) {
            return new VoteResultResponse("PROPOSTA APROVADA", approvedCount, rejectedCount);
        } else if (rejectedCount > approvedCount) {
            return new VoteResultResponse("PROPOSTA REJEITADA", approvedCount, rejectedCount);
        } else {
            return new VoteResultResponse("EMPATE", approvedCount, rejectedCount);
        }
    }
}





