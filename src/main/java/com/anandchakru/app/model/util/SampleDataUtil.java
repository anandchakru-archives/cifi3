package com.anandchakru.app.model.util;

import static com.anandchakru.app.model.constants.Cifi3.NO_SIGN;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.anandchakru.app.entity.App;
import com.anandchakru.app.entity.AppHistory;
import com.anandchakru.app.entity.AppNode;
import com.anandchakru.app.entity.AppPipe;
import com.anandchakru.app.entity.AppScm;
import com.anandchakru.app.model.enums.HistoryStatus;
import com.anandchakru.app.model.enums.PipeAgent;
import com.anandchakru.app.model.enums.ScmAgent;

public class SampleDataUtil {
	public static final String APP_NAME = "cifi3";
	public static final String APP_PIPE_NAME = APP_NAME;
	public static final String ASSET_ID = "9749549";
	public static final String CLUSTER_NAME = "cluster1";
	public static final String APP_NODE_NAME = "xps13";
	public static final String PIPE_USER_NAME = "builder";
	public static final String PIPE_TOKEN = "0fb89a7887bf0cd67b2bcf536c357396";
	public static final String BUILD_TRIIGER_TOKEN = "SimpleTriggerBuildAuthTokenForWebhookJunits";
	public static final String SCM_TOKEN = "SimpleSecretForWebhookJunits";
	public static final String TEXT_URI_LIST = "text/uri-list";
	public static final String SELF_URL = "http://localhost:9090/";
	public static final String HELLO_JTEST = "hello from jtest";
	public static final Boolean IS_WIN = System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;

	public static <T> String getUri(ResponseEntity<T> response) {
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return null;
		}
		return response.getHeaders().getLocation().toString();
	}
	public static <T> HttpEntity<T> makeJsonEntity(T payload) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<T>(payload, headers);
	}
	public static <T> HttpEntity<T> makeTextUriEntity(T payload) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Content-Type", TEXT_URI_LIST);
		return new HttpEntity<T>(payload, headers);
	}
	public static App makeApp() {
		return new App(APP_NAME);
	}
	public static AppHistory makeAppHistory() {
		AppHistory appHistory = new AppHistory();
		appHistory.setAssetId(ASSET_ID);
		appHistory.setAssetUrl("https://github.com/anandchakru/cifi3/releases/download/v1.0.0/cifi3-1.0.0.jar");
		appHistory.setCommitId("c52569b8f9da16479fa74d747f2292e63aac0e6c");
		appHistory.setStatus(HistoryStatus.BUILT);
		appHistory.setTag("v1.0.125");
		appHistory.setTime(new Timestamp(System.currentTimeMillis()));
		appHistory.setVersion("14063025");
		return appHistory;
	}
	public static AppNode makeAppNode() {
		AppNode appNode = new AppNode();
		appNode.setCluster(CLUSTER_NAME);
		appNode.setHeartbeatUrl("http://localhost:8080/heartbeat");
		appNode.setSelfUrl(SELF_URL);
		appNode.setIp("192.168.1.18");
		appNode.setNodeName(APP_NODE_NAME);
		appNode.setArtiFileName("cifi3-1.0.0.jar");
		if (IS_WIN) {
			appNode.setStageDir(Paths.get(".").toAbsolutePath().normalize().toString());
			appNode.setFetchScript("jtest.cmd");
			appNode.setStartScript("jtest.cmd");
			appNode.setShutdownScript("jtest.cmd");
		} else {
			appNode.setStageDir("/var/www/cifi3/"); //dir to store
			appNode.setFetchScript("fetch.sh");
			appNode.setStartScript("start.sh");
			appNode.setShutdownScript("shut.sh");
		}
		return appNode;
	}
	public static AppScm makeAppScm() {
		AppScm appScm = new AppScm();
		appScm.setAgent(ScmAgent.GITHUB);
		appScm.setApiToken(SCM_TOKEN);
		appScm.setApiUrl("https://api.github.com/repos/anandchakru/cifi3/");
		appScm.setScmAppId("156303659");
		appScm.setSignVerifyToken(NO_SIGN);
		appScm.setSignVerifyHeader("X-Hub-Signature");
		appScm.setEventHeader("X-GitHub-Event");
		appScm.setUrl("https://github.com/anandchakru/cifi3");
		return appScm;
	}
	public static AppPipe makeAppPipe() {
		AppPipe appPipe = new AppPipe();
		appPipe.setAgent(PipeAgent.JENKINS);
		appPipe.setApiToken(PIPE_TOKEN);
		appPipe.setBotBuild(false);
		appPipe.setBotBuildRegex("refs/heads/master");
		appPipe.setBotDeploy(false);
		appPipe.setBotDeployCondition("");
		appPipe.setBuildTriggerToken(BUILD_TRIIGER_TOKEN);
		appPipe.setName(APP_PIPE_NAME);
		appPipe.setSignVerifyHeader("X-Hub-Signature");
		appPipe.setSignVerifyToken(NO_SIGN);
		appPipe.setUrl("http://192.168.1.7:8080");
		appPipe.setUser(PIPE_USER_NAME);
		return appPipe;
	}
}
