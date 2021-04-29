package com.app.buys.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class AppBuysEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppBuysEurekaApplication.class, args);
	}

}
