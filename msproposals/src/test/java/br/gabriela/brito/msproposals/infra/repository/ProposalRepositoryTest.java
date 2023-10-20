package br.gabriela.brito.msproposals.infra.repository;

import br.gabriela.brito.msproposals.domain.Proposal;
import br.gabriela.brito.msproposals.infra.repository.ProposalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProposalRepositoryTest {

    @Autowired
    private ProposalRepository proposalRepository;

    @Test
    public void testFindByCpf() {

        Proposal proposal1 = new Proposal();
        proposal1.setCpf("12345678900");

        proposalRepository.save(proposal1);

        List<Proposal> foundProposals = proposalRepository.findByCpf("12345678900");

        assertEquals(1, foundProposals.size());
        assertEquals("12345678900", foundProposals.get(0).getCpf());

    }
}