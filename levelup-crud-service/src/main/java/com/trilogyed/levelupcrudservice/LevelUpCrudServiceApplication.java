package com.trilogyed.levelupcrudservice;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
@EnableDiscoveryClient
//@EnableSwagger2
public class LevelUpCrudServiceApplication {
	public static final String TOPIC_EXCHANGE_NAME="points-exchange";
		public static final String QUEUE_NAME = "points-queue";
		public static final String ROUTING_KEY = "levelup.#";

		@Bean
		Queue queue() {
			return new Queue(QUEUE_NAME, false);
		}

		@Bean
		TopicExchange exchange() {
			return new TopicExchange(TOPIC_EXCHANGE_NAME);
		}

		@Bean
		Binding binding(Queue queue, TopicExchange exchange) {
			return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
		}

		@Bean
		public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
			return new Jackson2JsonMessageConverter();
		}
	public static void main(String[] args) {
		SpringApplication.run(LevelUpCrudServiceApplication.class, args);
	}

}
