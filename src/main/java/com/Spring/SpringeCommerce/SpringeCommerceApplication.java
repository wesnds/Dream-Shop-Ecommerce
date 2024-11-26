package com.Spring.SpringeCommerce;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "ecommerce Shopping Cart",
				description = "ecommerce Shopping Cart with Spring Security, JWT",
				version = "v1.0"
		),
		externalDocs = @ExternalDocumentation(
				description = "shopping cart docs",
				url = "https://github.com/wesnds/ecommerce-dreams"
		)
)
public class SpringeCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringeCommerceApplication.class, args);
	}

}
