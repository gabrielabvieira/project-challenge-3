package br.gabriela.brito.msvoting.infra.clients.infra.repository;

import br.gabriela.brito.msvoting.domain.model.VoteResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteResultRepository extends JpaRepository<VoteResult, Long> {
    List<VoteResult> findBySessionId(Long sessionId);
}
