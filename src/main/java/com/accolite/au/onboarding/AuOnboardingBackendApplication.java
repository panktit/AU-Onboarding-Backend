package com.accolite.au.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AuOnboardingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuOnboardingBackendApplication.class, args);
		System.out.println(">>>>>> Run successful!");
	}
}
