package br.gabriela.brito.msproposals.application.representation;

import br.gabriela.brito.msproposals.domain.Proposal;
import lombok.Data;

@Data
public class ProposalSaveRequest {

    private String nome;
    private String descricao;
    private String cpf;

    public Proposal toModel(){
        return new Proposal(nome, descricao, cpf);
    }

}
