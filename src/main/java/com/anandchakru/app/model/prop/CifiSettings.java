package com.anandchakru.app.model.prop;

import java.io.Serializable;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@SuppressWarnings("serial")
@ConfigurationProperties(prefix = "cifi", ignoreUnknownFields = true)
public class CifiSettings implements Serializable {
	/**
	 * Url where cifi is exposed to clients.
	 * <br/>
	 * eg: http://localhost:9090/
	 */
	private String selfurl;
	/**
	 * Name of the application being cifi'd
	 * <br/>
	 * eg: myawesomeapp
	 */
	private String appname;
	private Map<String, String> faults;
}