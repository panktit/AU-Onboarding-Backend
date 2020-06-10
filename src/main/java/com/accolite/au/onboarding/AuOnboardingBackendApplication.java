package com.accolite.au.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class AuOnboardingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuOnboardingBackendApplication.class, args);
		System.out.println(">>>>>> Run successful!");
		log.info("Application started");
	}
}
