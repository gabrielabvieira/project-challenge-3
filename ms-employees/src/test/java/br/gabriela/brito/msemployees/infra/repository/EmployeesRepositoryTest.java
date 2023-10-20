package br.gabriela.brito.msemployees.infra.repository;

import br.gabriela.brito.msemployees.application.EmployeesService;
import br.gabriela.brito.msemployees.domain.Employees;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeesRepositoryTest {

    @Mock
    private EmployeesRepository employeesRepository;

    @InjectMocks
    private EmployeesService employeesService;
    @Test
    void findByCpf() {
        String cpf = "12345678900";
        Employees mockEmployees = new Employees(cpf, "Nome", 30);

        when(employeesRepository.findByCpf(anyString())).thenReturn(Optional.of(mockEmployees));

        Optional<Employees> result = employeesService.getByCPF(cpf);

        assertEquals(mockEmployees, result.orElse(null));
    }
}