package com.example.spring_security_5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurity5Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity5Application.class, args);
	}

}
