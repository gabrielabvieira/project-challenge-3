package br.gabriela.brito.msvoting.application.representation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoteResultResponse {
    private String status;
    private int approvedCount;
    private int rejectedCount;
}

