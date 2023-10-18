package br.gabriela.brito.msproposals.application.ex;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter

public class ApiError {
    private int status;
    private LocalDateTime timestamp;
    private String error;
    private String message;
    private String path;
}
