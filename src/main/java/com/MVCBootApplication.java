package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class MVCBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MVCBootApplication.class, args);
	}

}
