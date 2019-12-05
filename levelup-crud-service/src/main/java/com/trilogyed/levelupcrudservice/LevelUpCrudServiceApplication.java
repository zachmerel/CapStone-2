package com.trilogyed.levelupcrudservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LevelUpCrudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LevelUpCrudServiceApplication.class, args);
	}

}
