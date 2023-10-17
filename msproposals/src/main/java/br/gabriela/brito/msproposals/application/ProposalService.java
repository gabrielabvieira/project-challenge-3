package br.gabriela.brito.msproposals.application;

import br.gabriela.brito.msproposals.domain.Proposal;
import br.gabriela.brito.msproposals.infra.repository.ProposalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository repository;

    @Transactional
    public Long save(Proposal proposal) {
        Proposal savedProposal = repository.save(proposal);
        return savedProposal.getId();
    }

    public Optional<Proposal> getDataByProposal(Long id){
        return repository.findById(id);
    }
}
