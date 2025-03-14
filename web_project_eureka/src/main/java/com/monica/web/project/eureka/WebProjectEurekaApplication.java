package com.monica.web.project.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WebProjectEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebProjectEurekaApplication.class, args);
	}

}
