package br.gabriela.brito.msemployees.application;

import br.gabriela.brito.msemployees.domain.Employees;
import br.gabriela.brito.msemployees.infra.repository.EmployeesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeesService {

    private final EmployeesRepository repository;

    @Transactional
    public Employees save(Employees employees){
        return repository.save(employees);
    }

    public Optional<Employees> getByCPF(String cpf){
        return repository.findByCpf(cpf);
    }
}
