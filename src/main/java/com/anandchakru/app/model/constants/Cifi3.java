package com.anandchakru.app.model.constants;

import java.io.File;
import java.nio.charset.Charset;
import org.springframework.http.MediaType;

public class Cifi3 {
	public static final String SUCCESS = "Success";
	public static final String NO_SIGN = "No-Sign";
	//
	public static final String LOCAL_BASE = "http://localhost:9090";
	public static final String REPO_BASE = "/repo";
	public static final String APP_URI = "/app";
	public static final String APP_NODE_URI = "/app-node";
	public static final String APP_SCM_URI = "/app-scm";
	public static final String APP_PIPE_URI = "/app-pipe";
	public static final String APP_HISTORY_URI = "/app-history";
	public static final String APP_REPO_URI = REPO_BASE + APP_URI;
	public static final String APP_NODE_REPO_URI = REPO_BASE + APP_NODE_URI;
	public static final String APP_SCM_REPO_URI = REPO_BASE + APP_SCM_URI;
	public static final String APP_PIPE_REPO_URI = REPO_BASE + APP_PIPE_URI;
	public static final String APP_HISTORY_REPO_URI = REPO_BASE + APP_HISTORY_URI;
	//
	public static final String NO_DESCRIPTION = "";
	//
	public static final MediaType JSON_HAL = new MediaType("application", "hal+json", Charset.forName("UTF-8"));
	//
	public static final String SCRIPT_PATH = "scripts" + File.separator;
	public static final String APP_DEFAULT_PROFILE = "default";

	private Cifi3() {
	}
}
