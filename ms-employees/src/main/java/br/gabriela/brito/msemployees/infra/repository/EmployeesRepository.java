package br.gabriela.brito.msemployees.infra.repository;

import br.gabriela.brito.msemployees.domain.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    Optional<Employees> findByCpf(String cpf);
}
