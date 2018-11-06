package com.anandchakru.app.service;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class PostInitRunner implements CommandLineRunner {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void run(String... args) throws Exception {
		logger.debug(this.getClass().getCanonicalName() + " initialized with:" + Arrays.toString(args));
	}
}