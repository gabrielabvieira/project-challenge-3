package br.gabriela.brito.msemployees.application;

import br.gabriela.brito.msemployees.application.EmployeesService;
import br.gabriela.brito.msemployees.domain.Employees;
import br.gabriela.brito.msemployees.infra.repository.EmployeesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class EmployeesServiceTest {

    @Mock
    private EmployeesRepository employeesRepository;

    @InjectMocks
    private EmployeesService employeesService;

    @Test
    public void testSaveEmployee() {

        Employees mockEmployees = new Employees("12345678900", "Nome", 30);

        when(employeesRepository.save(any(Employees.class))).thenReturn(mockEmployees);

        Employees result = employeesService.save(mockEmployees);

        verify(employeesRepository, times(1)).save(mockEmployees);


        assertEquals(mockEmployees, result);
    }
    @Test
    public void testGetEmployeeByCPF() {

        Employees mockEmployees = new Employees("12345678900", "Nome", 30);
        when(employeesRepository.findByCpf("12345678900")).thenReturn(Optional.of(mockEmployees));

        Optional<Employees> result = employeesService.getByCPF("12345678900");

        verify(employeesRepository, times(1)).findByCpf("12345678900");

        assertTrue(result.isPresent());
        assertEquals(mockEmployees, result.get());
    }
}