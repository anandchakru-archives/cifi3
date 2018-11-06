package com.anandchakru.app.model.prop;

import java.io.Serializable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
@ConfigurationProperties(prefix = "cifi", ignoreUnknownFields = true)
public class CifiSettings implements Serializable {
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}