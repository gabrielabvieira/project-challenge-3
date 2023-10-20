package br.gabriela.brito.msvoting.application.representation;

import lombok.Data;

@Data
public class VoteRequest {

    private Long idSessao;
    private String voto;
    private String cpfFuncionario;
}
