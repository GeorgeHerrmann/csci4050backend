package com.csci4050.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The main class for the backend application.
 * Likely will not need to be modified.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.csci4050.user.repository") 
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
