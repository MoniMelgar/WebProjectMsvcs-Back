package com.monica.web.project.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.monica.web.project.commons.models.entity"})
public class WebProjectClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebProjectClientesApplication.class, args);
	}

}
