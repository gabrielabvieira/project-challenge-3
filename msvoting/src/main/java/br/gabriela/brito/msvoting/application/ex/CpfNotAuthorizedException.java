package br.gabriela.brito.msvoting.application.ex;

public class CpfNotAuthorizedException extends VotingException{
    public CpfNotAuthorizedException(String message){
        super(message);
    }
}
