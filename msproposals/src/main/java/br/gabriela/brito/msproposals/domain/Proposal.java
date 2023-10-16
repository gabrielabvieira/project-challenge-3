package br.gabriela.brito.msproposals.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Entity
@Data
@NoArgsConstructor
public class proposal {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descrição;
    private String cpf;

    public proposal(String nome,
                    String descrição,
                    String cpf) {
        this.nome = nome;
        this.descrição = descrição;
        this.cpf = cpf;
    }
}
