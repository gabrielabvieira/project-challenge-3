package br.gabriela.brito.msproposals.application.ex;

public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message){
        super(message);
    }
}
