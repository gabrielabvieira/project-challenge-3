package br.gabriela.brito.msvoting.domain;

import java.time.LocalDateTime;

public class VotingSession {
    private Long id;
    private Long proposalId;
    private String proposalName;
    private String proposalDescription;
    private String employeeCpf;
    private LocalDateTime creationDate;
    private LocalDateTime sessionEnd;
}
