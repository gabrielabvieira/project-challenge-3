package br.gabriela.brito.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MscloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscloudgatewayApplication.class, args);
	}
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder
				.routes()
				    .route(r -> r.path("/api/v1/employees/**").uri("lb://msemployees"))
				    .route(r -> r.path("/api/v1/proposals/**").uri("lb://msproposals"))
				    .route(r -> r.path("/api/v1/voting-session/**").uri("lb://msvoting"))
					.route(r -> r.path("/api/v1/votes/**").uri("lb://msvoting"))
					.route(r -> r.path("/api/v1/vote-results/**").uri("lb://msvoting"))
				.build();

	}

}
