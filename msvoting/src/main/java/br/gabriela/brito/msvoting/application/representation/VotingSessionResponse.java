package br.gabriela.brito.msvoting.application.representation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VotingSessionResponse {
    private Long id;
    private Long idProposta;
    private String cpfFuncionario;
    private LocalDateTime tempoSessao;
    private String nomeProposta;
    private String descricao;
}