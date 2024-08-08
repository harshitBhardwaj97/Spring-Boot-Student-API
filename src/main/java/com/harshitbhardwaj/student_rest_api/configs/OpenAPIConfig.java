package com.harshitbhardwaj.student_rest_api.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI myOpenAPI() {

		Contact contact = new Contact();
		contact.setEmail("harshitbhardwaj97@gmail.com");
		contact.setName("Harshit Bhardwaj");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("Student RESTful CRUD API").version("1.0").contact(contact)
				.description("A Student RESTful CRUD API built with Spring Boot 3 ").license(mitLicense);

		return new OpenAPI().info(info);
	}

}
