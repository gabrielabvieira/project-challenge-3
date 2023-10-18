package br.gabriela.brito.msproposals.application.ex;

public class NotFoundException extends ApiException{
    public NotFoundException(String message){
        super(message);
    }
}
