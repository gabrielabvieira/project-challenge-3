package br.gabriela.brito.msemployees.application.representation;

import br.gabriela.brito.msemployees.domain.Employees;
import lombok.Data;

@Data
public class EmployeesSaveRequest {
    private String cpf;
    private String nome;
    private Integer idade;

    public Employees toModel(){
        return new Employees(cpf, nome, idade);
    }
}
