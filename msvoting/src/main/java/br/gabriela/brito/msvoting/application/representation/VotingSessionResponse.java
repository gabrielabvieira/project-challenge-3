package br.gabriela.brito.msvoting.application.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotingSessionResponse {
    private Long id;
    private Long proposalId;
    private String employeeCpf;
    private LocalDateTime sessionEnd;
}
