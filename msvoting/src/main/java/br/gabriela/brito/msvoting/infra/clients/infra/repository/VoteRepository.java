package br.gabriela.brito.msvoting.infra.clients.infra.repository;

import br.gabriela.brito.msvoting.domain.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsBySessionIdAndCpfFuncionario(Long sessionId, String cpfFuncionario);
}
