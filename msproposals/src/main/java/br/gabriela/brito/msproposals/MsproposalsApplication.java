package br.gabriela.brito.msproposals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsproposalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsproposalsApplication.class, args);
	}

}
