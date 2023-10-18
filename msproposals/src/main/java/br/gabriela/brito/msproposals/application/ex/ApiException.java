package br.gabriela.brito.msproposals.application.ex;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
