package br.gabriela.brito.msvoting.application.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalResponse {
    private Long id;
    private String nome;
    private String descricao;
    private String cpf;
    private LocalDateTime dataCriacao;
}
