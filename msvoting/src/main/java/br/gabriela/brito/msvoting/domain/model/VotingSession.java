package br.gabriela.brito.msvoting.domain.model;

import br.gabriela.brito.msvoting.application.representation.VotingSessionResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class VotingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long proposalId;
    private String employeeCpf;
    private LocalDateTime sessionEnd;

    public VotingSessionResponse toResponse(){
        return VotingSessionResponse.builder()
                .id(id)
                .idProposta(proposalId)
                .cpfFuncionario(employeeCpf)
                .tempoSessao(sessionEnd)
                .build();
    }
}
