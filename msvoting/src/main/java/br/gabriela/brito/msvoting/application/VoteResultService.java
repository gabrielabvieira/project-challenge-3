package br.gabriela.brito.msvoting.application;

import br.gabriela.brito.msvoting.application.representation.VoteResultResponse;
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

    public VoteResultResponse getVoteResult(Long sessionId) {
        List<VoteResult> voteResults = voteResultRepository.findBySessionId(sessionId);

        int approvedCount = 0;
        int rejectedCount = 0;

        for (VoteResult voteResult : voteResults) {
            if ("Aprovar".equalsIgnoreCase(voteResult.getLastVote())) {
                approvedCount++;
            } else if ("Rejeitar".equalsIgnoreCase(voteResult.getLastVote())) {
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
