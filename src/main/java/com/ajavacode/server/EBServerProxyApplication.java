package com.ajavacode.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EBServerProxyApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(EBServerProxyApplication.class);
		springApplication.setAdditionalProfiles("dev");
		springApplication.setWebApplicationType(WebApplicationType.NONE);
		springApplication.run(args);
	}
}
