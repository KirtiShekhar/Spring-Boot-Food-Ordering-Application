package com.springboot.menuservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Menu Service", version = "2.0", description = "Spring Boot Application consisting of advanced concepts Named MenuService"))
@EnableEurekaClient
public class MenuServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenuServiceApplication.class, args);
	}

}
