package com.anandchakru.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetaController {
	@GetMapping("/hb")
	public String hb() {
		return System.currentTimeMillis() + ": Heatbeat";
	}
}
