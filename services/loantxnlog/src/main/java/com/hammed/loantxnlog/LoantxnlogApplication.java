package com.hammed.loantxnlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.hammed"})
@EntityScan(basePackages = "com.hammed.loanmanagement.models")  // Path to your entity classes
@EnableJpaRepositories(basePackages = "com.hammed.loanmanagement.repository")
@SpringBootApplication
public class LoantxnlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoantxnlogApplication.class, args);
	}

}
