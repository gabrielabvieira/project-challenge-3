package br.gabriela.brito.msvoting.infra.clients.infra.repository;

import br.gabriela.brito.msvoting.infra.clients.infra.repository.VotingSessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VotingSessionRepositoryTest {

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    @Test
    public void testFindById() {

        Long id = 1L;

        var result = votingSessionRepository.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isFalse();
    }
}