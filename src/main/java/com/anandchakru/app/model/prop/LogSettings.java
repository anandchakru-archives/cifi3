package com.anandchakru.app.model.prop;

import java.io.Serializable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
@ConfigurationProperties(prefix = "logging", ignoreUnknownFields = true)
public class LogSettings implements Serializable {
	private String file;

	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
}