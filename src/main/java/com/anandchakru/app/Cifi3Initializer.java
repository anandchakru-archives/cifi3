package com.anandchakru.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.anandchakru.app.config.Cifi3Config;

@SpringBootApplication
public class Cifi3Initializer {
	private static final Logger logger = LoggerFactory.getLogger("com.anandchakru.app.Cifi3Initializer");

	public static void main(String[] args) throws Exception {
		for (String arg : args) {
			System.out.println("Cifi3Initializer arg:" + arg);
		}
		SpringApplication.run(Cifi3Config.class, args);
		logger.debug("Initialized Cifi3Initializer.");
	}
}