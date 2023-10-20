package br.gabriela.brito.msvoting.domain.model;

import jakarta.persistence.*;
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

public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "proposal_id")
    private Long proposalId;

    @Column(name = "employee_cpf")
    private String cpfFuncionario;

    @Column(name = "vote")
    private String voto;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;


    public Long getSessionId() {
        return id;
    }
}

