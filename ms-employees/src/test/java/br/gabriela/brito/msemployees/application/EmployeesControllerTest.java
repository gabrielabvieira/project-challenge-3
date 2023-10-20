package br.gabriela.brito.msemployees.application;

import br.gabriela.brito.msemployees.application.representation.EmployeesSaveRequest;
import br.gabriela.brito.msemployees.domain.Employees;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class EmployeesControllerTest {

    @Mock
    private EmployeesService employeesService;

    @InjectMocks
    private EmployeesController employeesController;

    @Test
    public void testSaveEmployee() throws Exception {

        Employees mockEmployees = new Employees("12345678900", "Nome", 30);
        when(employeesService.save(Mockito.any(Employees.class))).thenReturn(mockEmployees);

        EmployeesSaveRequest request = new EmployeesSaveRequest();
        request.setCpf("12345678900");
        request.setNome("Nome");
        request.setIdade(30);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(employeesController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetEmployeeByCpf() throws Exception {

        Employees mockEmployees = new Employees("12345678900", "Nome", 30);
        when(employeesService.getByCPF(anyString())).thenReturn(Optional.of(mockEmployees));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(employeesController).build();
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees")
                        .param("cpf", "12345678900")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678900"));
    }
}

