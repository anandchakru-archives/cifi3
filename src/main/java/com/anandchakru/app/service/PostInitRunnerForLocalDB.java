package com.anandchakru.app.service;

import static com.anandchakru.app.model.constants.Cifi3.APP_HISTORY_REPO_URI;
import static com.anandchakru.app.model.constants.Cifi3.APP_NODE_REPO_URI;
import static com.anandchakru.app.model.constants.Cifi3.APP_PIPE_REPO_URI;
import static com.anandchakru.app.model.constants.Cifi3.APP_REPO_URI;
import static com.anandchakru.app.model.constants.Cifi3.APP_SCM_REPO_URI;
import static com.anandchakru.app.model.constants.Cifi3.APP_URI;
import static com.anandchakru.app.model.constants.Cifi3.LOCAL_BASE;
import static com.anandchakru.app.model.constants.Profile.LOCAL;
import static com.anandchakru.app.model.util.SampleDataUtil.getUri;
import static com.anandchakru.app.model.util.SampleDataUtil.makeApp;
import static com.anandchakru.app.model.util.SampleDataUtil.makeAppHistory;
import static com.anandchakru.app.model.util.SampleDataUtil.makeAppNode;
import static com.anandchakru.app.model.util.SampleDataUtil.makeAppPipe;
import static com.anandchakru.app.model.util.SampleDataUtil.makeAppScm;
import static com.anandchakru.app.model.util.SampleDataUtil.makeJsonEntity;
import static com.anandchakru.app.model.util.SampleDataUtil.makeTextUriEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.anandchakru.app.entity.AppNode;
import com.anandchakru.app.model.prop.CifiSettings;

@Profile({ LOCAL })
@Order(1)
@Service
public class PostInitRunnerForLocalDB implements CommandLineRunner {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CifiSettings settings;
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	RestTemplate rest;

	@Override
	public void run(String... args) {
		try {
			cleanDB();
			String appURI = addApp();
			addAppHistory(appURI);
			addAppNode(appURI);
			addAppScm(appURI);
			addAppPipe(appURI);
		} catch (HttpServerErrorException e) {
			logger.warn("ResponseBody:" + e.getResponseBodyAsString() + "~EOF~", e);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
	}
	private void cleanDB() {
		jdbc.execute("delete from app_history;");
		jdbc.execute("delete from app_node;");
		jdbc.execute("delete from app_pipe;");
		jdbc.execute("delete from app_scm;");
		jdbc.execute("delete from app;");
	}
	public String addApp() {
		return getUri(rest.exchange(LOCAL_BASE + APP_REPO_URI, HttpMethod.POST,
				makeJsonEntity(makeApp(settings.getAppname())), Object.class));
	}
	public String addAppHistory(String appURI) {
		return linkAppChildToApp(getUri(rest.exchange(LOCAL_BASE + APP_HISTORY_REPO_URI, HttpMethod.POST,
				makeJsonEntity(makeAppHistory()), Object.class)), appURI);
	}
	public String addAppNode(String appURI) {
		AppNode appNode = makeAppNode();
		appNode.setStageDir("C:\\Users\\anand\\ews\\test\\trx");
		appNode.setFetchScript("fetch.ps1");
		appNode.setStartScript("startup.ps1");
		appNode.setShutdownScript("shut.ps1");
		return linkAppChildToApp(getUri(
				rest.exchange(LOCAL_BASE + APP_NODE_REPO_URI, HttpMethod.POST, makeJsonEntity(appNode), Object.class)),
				appURI);
	}
	public String addAppScm(String appURI) {
		return linkAppChildToApp(getUri(rest.exchange(LOCAL_BASE + APP_SCM_REPO_URI, HttpMethod.POST,
				makeJsonEntity(makeAppScm()), Object.class)), appURI);
	}
	public String addAppPipe(String appURI) {
		return linkAppChildToApp(getUri(rest.exchange(LOCAL_BASE + APP_PIPE_REPO_URI, HttpMethod.POST,
				makeJsonEntity(makeAppPipe()), Object.class)), appURI);
	}
	private String linkAppChildToApp(String appChildURI, String appURI) {
		if (StringUtils.isNotEmpty(appChildURI) && StringUtils.isNotEmpty(appURI)) {
			rest.exchange(appChildURI + APP_URI, HttpMethod.PUT, makeTextUriEntity(appURI), Object.class);
		}
		return appChildURI;
	}
}