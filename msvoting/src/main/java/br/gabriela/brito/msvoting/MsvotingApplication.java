package br.gabriela.brito.msvoting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsvotingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvotingApplication.class, args);
	}

}
