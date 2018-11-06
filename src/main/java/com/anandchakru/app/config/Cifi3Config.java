package com.anandchakru.app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@EnableAsync
@Configuration
@EntityScan({ "com.anandchakru.app" })
@ComponentScan({ "com.anandchakru.app" })
public class Cifi3Config {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}