package br.gabriela.brito.msvoting.application.representation;

import lombok.Data;

@Data
public class VotingSessionSaveRequest {
    private String proposalId;
    private int sessionTime;
    private String employeeCpf;
}

