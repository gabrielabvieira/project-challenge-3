package br.gabriela.brito.msproposals.application;

import br.gabriela.brito.msproposals.application.ProposalService;
import br.gabriela.brito.msproposals.application.ex.ApiException;
import br.gabriela.brito.msproposals.application.representation.ProposalSaveRequest;
import br.gabriela.brito.msproposals.domain.Proposal;
import br.gabriela.brito.msproposals.infra.repository.ProposalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProposalServiceTest {

    @InjectMocks
    private ProposalService proposalService;

    @Mock
    private ProposalRepository proposalRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProposalWithException() {
        ProposalSaveRequest request = new ProposalSaveRequest();
        request.setNome("Test Proposal");
        request.setDescricao("Test Description");
        request.setCpf("12345678901");

        when(proposalRepository.save(any())).thenThrow(new RuntimeException("Simulated error during save"));

        assertThrows(ApiException.class, () -> proposalService.save(request));
    }


    @Test
    void testGetDataByProposal() {
        Long proposalId = 1L;

        Proposal mockProposal = new Proposal();
        mockProposal.setId(proposalId);
        mockProposal.setNome("Test Proposal");
        mockProposal.setDescricao("Test Description");
        mockProposal.setCpf("12345678901");
        mockProposal.setDataCriacao(LocalDateTime.now());

        when(proposalRepository.findById(proposalId)).thenReturn(Optional.of(mockProposal));

        Optional<Proposal> retrievedProposal = proposalService.getDataByProposal(proposalId);

        assertEquals(mockProposal, retrievedProposal.orElse(null));
    }
}