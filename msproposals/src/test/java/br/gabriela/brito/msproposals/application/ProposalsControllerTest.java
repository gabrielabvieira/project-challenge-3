package br.gabriela.brito.msproposals.application;

import br.gabriela.brito.msproposals.application.representation.ProposalSaveRequest;
import br.gabriela.brito.msproposals.application.representation.ProposalsByEmployeeResponse;
import br.gabriela.brito.msproposals.domain.Proposal;
import br.gabriela.brito.msproposals.application.ex.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



@ExtendWith(MockitoExtension.class)
public class ProposalsControllerTest {

    @Mock
    private ProposalService proposalService;

    @Mock
    private EmployeeProposalService employeeProposalService;

    @InjectMocks
    private ProposalsController proposalsController;

    @Test
    public void testCreateProposal() {

        ProposalSaveRequest request = new ProposalSaveRequest();
        request.setNome("Test Proposal");
        request.setDescricao("Test Description");
        request.setCpf("12345678900");

        Long proposalId = 1L;
        Mockito.when(proposalService.save(request)).thenReturn(proposalId);

        ResponseEntity<Map<String, String>> responseEntity = proposalsController.createProposal(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().containsKey("id_proposta"));
        assertEquals(proposalId.toString(), responseEntity.getBody().get("id_proposta"));
    }
    @Test
    public void testGetDataByProposal() {

        Long proposalId = 1L;

        Proposal mockProposal = new Proposal();
        mockProposal.setId(proposalId);
        mockProposal.setNome("Test Proposal");
        mockProposal.setDescricao("Test Description");
        mockProposal.setCpf("12345678900");

        Mockito.when(proposalService.getDataByProposal(proposalId)).thenReturn(Optional.of(mockProposal));

        ResponseEntity<Proposal> responseEntity = proposalsController.getDataByProposal(proposalId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockProposal, responseEntity.getBody());
    }

}
