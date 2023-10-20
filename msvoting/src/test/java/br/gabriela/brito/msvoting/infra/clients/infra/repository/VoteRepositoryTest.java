package br.gabriela.brito.msvoting.infra.clients.infra.repository;

import br.gabriela.brito.msvoting.domain.model.Vote;
import br.gabriela.brito.msvoting.infra.clients.infra.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class VoteRepositoryTest {

    @MockBean
    private VoteRepository voteRepository;

    @Test
    void existsBySessionIdAndCpfFuncionario_ExistingVote_ReturnsTrue() {

        Long sessionId = 1L;
        String cpfFuncionario = "12345678901";

        when(voteRepository.existsBySessionIdAndCpfFuncionario(sessionId, cpfFuncionario)).thenReturn(true);

        boolean result = voteRepository.existsBySessionIdAndCpfFuncionario(sessionId, cpfFuncionario);

        assertThat(result).isTrue();
    }

    @Test
    void existsBySessionIdAndCpfFuncionario_NonExistingVote_ReturnsFalse() {

        Long sessionId = 1L;
        String cpfFuncionario = "12345678901";

        when(voteRepository.existsBySessionIdAndCpfFuncionario(sessionId, cpfFuncionario)).thenReturn(false);

        boolean result = voteRepository.existsBySessionIdAndCpfFuncionario(sessionId, cpfFuncionario);

        assertThat(result).isFalse();
    }
    }
