package br.gabriela.brito.msvoting.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msemployees", path = "/api/v1/employees")
public interface EmployeesControllerClient {
    @GetMapping
    ResponseEntity <String> validateEmployee(@RequestParam("cpf") String cpf);

}
