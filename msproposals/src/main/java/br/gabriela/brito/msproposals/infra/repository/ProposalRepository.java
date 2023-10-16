package br.gabriela.brito.msproposals.infra.repository;

import br.gabriela.brito.msproposals.domain.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
