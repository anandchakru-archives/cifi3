package com.anandchakru.app.service;

import static com.anandchakru.app.model.constants.Cifi3.APP_DEFAULT_PROFILE;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anandchakru.app.entity.App;
import com.anandchakru.app.entity.AppHistory;
import com.anandchakru.app.entity.AppNode;
import com.anandchakru.app.entity.AppScm;
import com.anandchakru.app.model.enums.ExecuteType;
import com.anandchakru.app.model.enums.FaultCode;
import com.anandchakru.app.model.excep.AppServiceException;
import com.anandchakru.app.model.excep.ExceptionMeta;
import com.anandchakru.app.model.prop.CifiSettings;
import com.anandchakru.app.model.rsp.Response;
import com.anandchakru.app.model.rsp.ScriptIoRsp;
import com.anandchakru.app.repo.AppHistoryRestRepo;
import com.anandchakru.app.repo.AppNodeRestRepo;
import com.anandchakru.app.repo.AppScmRestRepo;
import com.google.common.collect.Lists;

@Service
public class ExecuteService {
	@Autowired
	private AppHistoryRestRepo appHistoryRepo;
	@Autowired
	private AppNodeRestRepo appNodeRepo;
	@Autowired
	private AppScmRestRepo appScmRepo;
	@Autowired
	private AssertService as;
	@Autowired
	private CifiSettings settings;

	public Response deploy(Long appHistoryId) {
		try {
			Optional<AppHistory> optAppHistory = appHistoryRepo.findById(appHistoryId);
			as.isTrue(optAppHistory.isPresent(), FaultCode.INVALID_APP_HISTORY);
			AppHistory appHistory = optAppHistory.get();
			App app = appHistory.getApp();
			as.isNotNull(app, FaultCode.INVALID_APP_FOR_HISTORY);
			AppNode appNode = appNodeRepo.findByApp_appIdAndSelfUrl(app.getAppId(), settings.getSelfurl());
			as.isNotNull(appNode, FaultCode.INVALID_APP_NODE_FOR_HISTORY);
			AppScm appScm = appScmRepo.findByApp_appId(app.getAppId());
			as.isNotNull(appScm, FaultCode.INVALID_APP_SCM_FOR_HISTORY);
			Response shutRsp = execute(ExecuteType.SHUTDOWN,
					appNode.getStageDir() + File.separator + appNode.getShutdownScript());
			// /var/www/cifi3/fetch.sh https://api.github.com/repos/anandchakru/cifi3/releases/assets/9453603?access_token=fc978ff1ea0f608e8d4abb2cb0f87242e3e8dcca cifi3-1.0.0.jar
			ScriptIoRsp fetchRsp = execute(ExecuteType.FETCH,
					appNode.getStageDir() + File.separator + appNode.getFetchScript(), appScm.getApiUrl()
							+ "releases/assets/" + appHistory.getAssetId() + "?access_token=" + appScm.getApiToken(),
					appNode.getArtiFileName());
			ScriptIoRsp startRsp = execute(ExecuteType.START,
					appNode.getStageDir() + File.separator + appNode.getStartScript());
			startRsp.addChild(shutRsp);
			startRsp.addChild(fetchRsp);
			return startRsp;
		} catch (AppServiceException e) {
			throw new AppServiceException(new ExceptionMeta(as.codeToFault(FaultCode.UNXPTD_ERR),
					new String[] { "deploy", "input:" + appHistoryId, e.toString() }));
		}
	}
	public Response shutdown(Long appId, Long appNodeId) throws InterruptedException {
		AppNode appNode = appNodeRepo.findByApp_appIdAndAppNodeId(appId, appNodeId);
		return shutdown(appNode);
	}
	public Response restart(Long appId, Long appNodeId) throws InterruptedException {
		AppNode appNode = appNodeRepo.findByApp_appIdAndAppNodeId(appId, appNodeId);
		ScriptIoRsp restartRsp = restart(appNode);
		return restartRsp;
	}
	public ScriptIoRsp execute(ExecuteType action, String... script) {
		try {
			Process process = new ProcessBuilder().command(script).start();
			process.waitFor();
			return new ScriptIoRsp(IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8),
					IOUtils.toString(process.getErrorStream(), StandardCharsets.UTF_8), Lists.newArrayList());
		} catch (IOException | InterruptedException e) {
			throw new AppServiceException(new ExceptionMeta(as.codeToFault(FaultCode.IO_WHILE_EXECUTE),
					new String[] { action.toString(), Arrays.toString(script) }));
		}
	}
	private Response shutdown(AppNode appNode) {
		as.isNotNull(appNode, FaultCode.INVLD_NODE_OR_APP);
		as.isNotEmpty(appNode.getShutdownScript(), FaultCode.SHUTDOWN_SCRPT_EMPTY);
		return execute(ExecuteType.SHUTDOWN, appNode.getStageDir() + File.separator + appNode.getShutdownScript());
	}
	private ScriptIoRsp restart(AppNode appNode) {
		as.isNotNull(appNode, FaultCode.INVLD_NODE_OR_APP);
		as.isNotEmpty(appNode.getShutdownScript(), FaultCode.SHUTDOWN_SCRPT_EMPTY);
		as.isNotEmpty(appNode.getStartScript(), FaultCode.START_SCRPT_EMPTY);
		Response shutdownRsp = shutdown(appNode);
		String springProfiles = appNode.getSpringProfiles();
		springProfiles = as.ifNullDefaultTo(springProfiles, APP_DEFAULT_PROFILE);
		ScriptIoRsp restartRsp = execute(ExecuteType.START,
				appNode.getStageDir() + File.separator + appNode.getStartScript(), appNode.getArtiFileName(),
				springProfiles);
		restartRsp.addChild(shutdownRsp);
		return restartRsp;
	}
}
