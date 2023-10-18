package br.gabriela.brito.msproposals.infra.repository;

import br.gabriela.brito.msproposals.domain.Proposal;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    List<Proposal> findByCpf(@Param("cpf") String cpf);
}
