package br.gabriela.brito.msvoting.application.representation;

import lombok.Data;

@Data
public class VotingSessionSaveRequest {
    private String idProposta;
    private Long tempoSessao;
    private String cpfFuncionario;
}


