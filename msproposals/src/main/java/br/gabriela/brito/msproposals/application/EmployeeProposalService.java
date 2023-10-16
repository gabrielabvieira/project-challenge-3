package br.gabriela.brito.msproposals.application;

import br.gabriela.brito.msproposals.domain.EmployeeProposal;
import br.gabriela.brito.msproposals.infra.repository.EmployeeProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeProposalService {

    private EmployeeProposalRepository repository;

    public List<EmployeeProposal> listProposalsByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
