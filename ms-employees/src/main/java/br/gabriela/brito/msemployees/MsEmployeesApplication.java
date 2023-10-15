package br.gabriela.brito.msemployees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient

public class MsEmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEmployeesApplication.class, args);
	}

}
