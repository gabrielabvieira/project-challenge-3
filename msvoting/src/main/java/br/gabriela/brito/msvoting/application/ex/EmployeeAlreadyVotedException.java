package br.gabriela.brito.msvoting.application.ex;

public class EmployeeAlreadyVotedException extends VotingException {
    public EmployeeAlreadyVotedException(String message) {
        super(message);
    }
}