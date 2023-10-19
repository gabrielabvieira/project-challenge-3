package br.gabriela.brito.msvoting.application;

import br.gabriela.brito.msvoting.application.representation.VotingSessionResponse;
import br.gabriela.brito.msvoting.application.representation.VotingSessionSaveRequest;
import br.gabriela.brito.msvoting.domain.model.VotingSession;
import br.gabriela.brito.msvoting.infra.clients.EmployeesControllerClient;
import br.gabriela.brito.msvoting.infra.clients.ProposalsControllerClient;
import br.gabriela.brito.msvoting.infra.clients.infra.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VotingSessionService {

    private final EmployeesControllerClient employeesClient;
    private final ProposalsControllerClient proposalsClient;
    private final VotingSessionRepository votingRepository;

    public VotingSessionResponse createVotingSession(VotingSessionSaveRequest request) {
        employeesClient.validateEmployee(request.getCpfFuncionario());

        LocalDateTime sessionEnd = LocalDateTime.now().plusMinutes(getSessionDuration(request.getTempoSessao()));

        VotingSession votingSession = VotingSession.builder()
                .proposalId(Long.parseLong(request.getIdProposta()))
                .employeeCpf(request.getCpfFuncionario())
                .sessionEnd(sessionEnd)
                .build();

        VotingSession savedSession = votingRepository.save(votingSession);

        return savedSession.toResponse();
    }

    private long getSessionDuration(Long specifiedDuration) {
        return specifiedDuration != null ? specifiedDuration : 1L;
    }
}

