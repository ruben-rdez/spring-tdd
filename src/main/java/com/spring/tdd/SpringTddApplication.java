package com.spring.tdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.spring.tdd")
@EntityScan("com.spring.tdd.model")
@EnableJpaRepositories("com.spring.tdd.repository")
public class SpringTddApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTddApplication.class, args);
	}

}
