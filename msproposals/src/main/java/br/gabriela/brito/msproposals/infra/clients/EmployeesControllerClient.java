package br.gabriela.brito.msproposals.infra.clients;

import br.gabriela.brito.msproposals.domain.Proposal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "msemployees", path = "/api/v1/employees")
public interface EmployeesControllerClient {

    @GetMapping
    void validateEmployee(@RequestParam("cpf") String cpf);
    @GetMapping
    List<Proposal> getProposalsByEmployee(@RequestParam("cpf") String cpf);
}