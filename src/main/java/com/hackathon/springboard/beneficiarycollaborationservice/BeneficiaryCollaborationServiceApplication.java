package com.hackathon.springboard.beneficiarycollaborationservice;

import com.hackathon.springboard.beneficiarycollaborationservice.configs.DynamoDbProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
		basePackages = {
				"org.openapitools.configuration",
				"com.hackathon.springboard.beneficiarycollaborationservice"
		}
)
@OpenAPIDefinition(
		servers = {
				@Server(url = "/", description = "Default Server URL")
		}
)
@EnableConfigurationProperties({
		DynamoDbProperties.class
})
public class BeneficiaryCollaborationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeneficiaryCollaborationServiceApplication.class, args);
	}

}
