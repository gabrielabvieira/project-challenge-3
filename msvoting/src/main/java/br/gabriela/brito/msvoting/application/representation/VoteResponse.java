package br.gabriela.brito.msvoting.application.representation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoteResponse {
    private Long idVoto;
    private String status;

    public VoteResponse(Long idVoto) {
        this.idVoto = idVoto;
        this.status = "Voto registrado";
    }
}
