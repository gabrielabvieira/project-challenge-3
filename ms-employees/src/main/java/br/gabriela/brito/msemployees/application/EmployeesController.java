package br.gabriela.brito.msemployees.application;

import br.gabriela.brito.msemployees.application.representation.EmployeesSaveRequest;
import br.gabriela.brito.msemployees.domain.Employees;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeesController {

    private final EmployeesService service;
    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservice de funcionários");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody EmployeesSaveRequest request){
        var employees = request.toModel();
        service.save(employees);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(employees.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dataEmployees(@RequestParam("cpf") String cpf){
        var employees = service.getByCPF(cpf);
        if(employees.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employees);
    }
}