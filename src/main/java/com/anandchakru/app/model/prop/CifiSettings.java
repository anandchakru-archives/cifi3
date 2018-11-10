package com.anandchakru.app.model.prop;

import java.io.Serializable;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("serial")
@ConfigurationProperties(prefix = "cifi", ignoreUnknownFields = true)
public class CifiSettings implements Serializable {
	private String name;
	private Map<String, String> faults;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getFaults() {
		return faults;
	}
	public void setFaults(Map<String, String> faults) {
		this.faults = faults;
	}
}