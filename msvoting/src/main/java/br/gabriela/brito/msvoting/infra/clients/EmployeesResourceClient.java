package br.gabriela.brito.msvoting.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "msemployees", path = "/api/v1/employees")
public interface EmployeesResourceClient {
    

}
