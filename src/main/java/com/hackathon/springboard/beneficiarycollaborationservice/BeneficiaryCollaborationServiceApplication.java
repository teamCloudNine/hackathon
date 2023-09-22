package com.hackathon.springboard.beneficiarycollaborationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
		basePackages = {
				"org.openapitools.configuration",
				"com.hackathon.springboard.beneficiarycollaborationservice"
		}
)
public class BeneficiaryCollaborationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeneficiaryCollaborationServiceApplication.class, args);
	}

}
