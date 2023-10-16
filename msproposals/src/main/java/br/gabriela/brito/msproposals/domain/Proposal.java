package br.gabriela.brito.msproposals.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Proposal {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descrição;
    private String cpf;

    public Proposal(String nome,
                    String descrição,
                    String cpf) {
        this.nome = nome;
        this.descrição = descrição;
        this.cpf = cpf;
    }
}
