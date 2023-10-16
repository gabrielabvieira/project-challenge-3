package br.gabriela.brito.msproposals.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Proposal {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String cpf;
    private LocalDateTime dataCriacao;

    @PrePersist
    protected void onCreate(){
        this.dataCriacao = LocalDateTime.now();
    }

    public LocalDateTime getDataCriacao(){
        return dataCriacao;
    }

    public Proposal(String nome,
                    String descricao,
                    String cpf) {
        this.nome = nome;
        this.descricao = descricao;
        this.cpf = cpf;
    }

}
