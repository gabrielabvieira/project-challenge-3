package br.gabriela.brito.msvoting.domain.model;

import br.gabriela.brito.msvoting.application.representation.VotingSessionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class VotingSession {
    private Long id;
    private Long proposalId;
    private String employeeCpf;
    private LocalDateTime sessionEnd;

    public VotingSessionResponse toResponse(){
        return VotingSessionResponse.builder()
                .id(id)
                .proposalId(proposalId)
                .employeeCpf(employeeCpf)
                .sessionEnd(sessionEnd)
                .build();
    }
}
