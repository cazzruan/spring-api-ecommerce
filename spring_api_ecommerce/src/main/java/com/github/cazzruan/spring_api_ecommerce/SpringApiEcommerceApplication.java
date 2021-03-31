package com.github.cazzruan.spring_api_ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.github.cazzruan.spring_api_ecommerce.domain.model")
@ComponentScan(basePackages = "com.github.cazzruan.spring_api_ecommerce.*")
@EnableJpaRepositories(basePackages = "com.github.cazzruan.spring_api_ecommerce.domain.repository")
@EnableAutoConfiguration
@SpringBootApplication
public class SpringApiEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiEcommerceApplication.class, args);
	}

}
