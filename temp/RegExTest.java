package com.anandchakru.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;

public class RegExTest {
	public static void main(String[] args) throws Exception {
		System.out.println("refs/heads/master".matches("refs/heads/master"));
	}
}