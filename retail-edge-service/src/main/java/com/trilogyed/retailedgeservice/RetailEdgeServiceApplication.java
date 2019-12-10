package com.trilogyed.retailedgeservice;

import com.trilogyed.retailedgeservice.dto.LevelUp;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@EnableCircuitBreaker
public class RetailEdgeServiceApplication {
	@Autowired
	private LevelUpService levelUpService;
	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		return builder.build();
	}
	@RequestMapping("/points")
	public List<LevelUp> getAllLevelUps(){
		return levelUpService.getLevelUp();
	}
	@Bean
		public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
			RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
			rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
			return rabbitTemplate;
		}
		@Bean
		public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
			return new Jackson2JsonMessageConverter();
		}
	public static void main(String[] args) {
		SpringApplication.run(RetailEdgeServiceApplication.class, args);
	}

}
