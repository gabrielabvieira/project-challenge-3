package br.gabriela.brito.msproposals.application.representation;

import br.gabriela.brito.msproposals.domain.EmployeeProposal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalsByEmployeeResponse {
    private Long id;
    private String nome;
    private String descricao;
    private String cpf;
    private String data;

    public static ProposalsByEmployeeResponse fromModel(EmployeeProposal model){
        return new ProposalsByEmployeeResponse(
                model.getProposal().getId(),
                model.getProposal().getNome(),
                model.getProposal().getDescricao(),
                model.getProposal().getCpf(),
                model.getProposal().getDataCriacao().toString()
        );
    }

}
