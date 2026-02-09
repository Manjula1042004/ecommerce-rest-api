package com.manjula.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApiApplication.class, args);
		System.out.println("✅ ECommerce API Started Successfully!");
		System.out.println("✅ H2 Console: http://localhost:8080/h2-console");
		System.out.println("✅ JDBC URL: jdbc:h2:mem:ecommercedb");
		System.out.println("✅ Username: sa");
		System.out.println("✅ Password: (leave empty)");
	}
}