package br.gabriela.brito.msproposals.infra.repository;

import br.gabriela.brito.msproposals.domain.EmployeeProposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeProposalRepository extends JpaRepository<EmployeeProposal, Long> {
    List<EmployeeProposal> findByCpf(String cpf);
}
