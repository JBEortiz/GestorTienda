package com.app.product.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class AppProductEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppProductEurekaApplication.class, args);
	}

}
