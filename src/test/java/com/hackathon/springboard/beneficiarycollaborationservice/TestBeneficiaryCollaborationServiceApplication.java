package com.hackathon.springboard.beneficiarycollaborationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestBeneficiaryCollaborationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(BeneficiaryCollaborationServiceApplication::main).with(TestBeneficiaryCollaborationServiceApplication.class).run(args);
	}

}
