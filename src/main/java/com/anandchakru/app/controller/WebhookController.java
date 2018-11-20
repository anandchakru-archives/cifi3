package com.anandchakru.app.controller;

import static com.anandchakru.app.model.constants.Cifi3.SUCCESS;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.anandchakru.app.entity.AppHistory;
import com.anandchakru.app.entity.AppPipe;
import com.anandchakru.app.entity.AppScm;
import com.anandchakru.app.model.enums.FaultCode;
import com.anandchakru.app.model.enums.HistoryStatus;
import com.anandchakru.app.model.excep.AppIllegalArgumentException;
import com.anandchakru.app.model.rsp.AppResponse;
import com.anandchakru.app.model.rsp.WebhookRsp;
import com.anandchakru.app.model.webhook.github.WebhookPushPayLoad;
import com.anandchakru.app.model.webhook.jenkins.JenkinsJobPayload;
import com.anandchakru.app.repo.AppHistoryRestRepo;
import com.anandchakru.app.repo.AppPipeRestRepo;
import com.anandchakru.app.repo.AppScmRestRepo;
import com.anandchakru.app.service.AssertService;
import com.anandchakru.app.service.PipeService;
import com.anandchakru.app.service.SignVerifyService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@RestController
public class WebhookController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SignVerifyService signVerifyService;
	@Autowired
	private PipeService buildr;
	private Gson gson = new Gson();
	@Autowired
	private AppPipeRestRepo appPipeRepo;
	@Autowired
	private AppHistoryRestRepo appHistoryRepo;
	@Autowired
	private AppScmRestRepo appScmRepo;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private AssertService as;

	@PostMapping("/webhook/cifi-scm")
	public @ResponseBody AppResponse scmWebhook(@RequestBody String payload) {
		WebhookPushPayLoad payloadObj = null;
		try {
			payloadObj = gson.fromJson(payload, WebhookPushPayLoad.class);
		} catch (JsonSyntaxException e) {
			logger.debug("JsonSyntaxException: " + payload, e);
		}
		as.isNotNull(payloadObj, FaultCode.INVALID_JSON);
		as.isNotNull(payloadObj.getRef(), FaultCode.INVALID_JSON);
		AppScm appScm = appScmRepo.findByScmAppId(String.valueOf(payloadObj.getRepository().getId()));//payloadObj.getRepository().getId()=89962281
		as.isNotNull(appScm, FaultCode.INVALID_SCM_APP_ID);
		as.isNotNull(appScm.getApp(), FaultCode.INVALID_APP_FOR_SCM);
		AppPipe appPipe = appPipeRepo.findByApp_appId(appScm.getApp().getAppId());
		as.isNotNull(appPipe, FaultCode.INVALID_APP_PIPE_FOR_SCM);
		WebhookRsp rsp = new WebhookRsp();
		// When a tag was created, payloadObj.getRef() = refs/tags/v1
		// When there is a push to master branch, payloadObj.getRef() = refs/heads/master. Build only for push to master
		if (!appPipe.getBotBuildRegex().matches(payloadObj.getRef())) { //if NOT "refs/heads/master"
			rsp.setValue(
					"Ignoring " + payloadObj.getRef() + ", no match for BotBuildRegex:" + appPipe.getBotBuildRegex());
			return new AppResponse(rsp, null);
		}
		as.isTrue(signVerifyService.verify(payload, request.getHeader(appScm.getSignVerifyHeader()),
				appScm.getSignVerifyToken()), FaultCode.INVALID_WEBHOOK_SIGN);
		String event = request.getHeader(appScm.getEventHeader());//Eg: push
		logger.debug("ScmEvent: " + event);
		AppHistory appHistory = appHistoryRepo.findByApp_AppIdAndCommitId(appScm.getApp().getAppId(),
				payloadObj.getHead_commit().getId());
		as.isNull(appHistory, FaultCode.INVALID_SCM_APP_ID);
		appHistory = new AppHistory();
		appHistory.setApp(appScm.getApp());
		appHistory.setCommitId(payloadObj.getHead_commit().getId());
		appHistory.setStatus(HistoryStatus.BUILD_START);
		appHistory.setTime(new Timestamp(System.currentTimeMillis()));
		try {
			rsp.setValue(buildr.build(appPipe, payloadObj.getHead_commit().getId()));
		} catch (AppIllegalArgumentException e) {
			appHistory.setStatus(HistoryStatus.BUILD_FAILED);
			rsp.setValue("Excepton: " + e);
		}
		appHistoryRepo.save(appHistory);
		return new AppResponse(rsp, null);
	}
	@PostMapping("/webhook/cifi-pipe")
	public @ResponseBody AppResponse acceptJenkins(@RequestBody String payload) {
		WebhookRsp rsp = new WebhookRsp();
		JenkinsJobPayload payloadObj = null;
		try {
			logger.debug("payload:" + payload);
			payloadObj = gson.fromJson(payload, JenkinsJobPayload.class);
		} catch (JsonSyntaxException e) {
			logger.debug("JsonSyntaxException: " + payload, e);
		}
		as.isNotNull(payloadObj, FaultCode.INVALID_JSON);
		as.isNotNull(payloadObj.getAppId(), FaultCode.INVALID_JSON);
		AppPipe appPipe = appPipeRepo.findByApp_appId(payloadObj.getAppId());
		as.isNotNull(appPipe, FaultCode.INVALID_PIPE_APP_ID);
		as.isNotNull(appPipe.getApp(), FaultCode.INVALID_APP_FOR_PIPE);
		as.isTrue(signVerifyService.verify(payload, request.getHeader(appPipe.getSignVerifyHeader()),
				appPipe.getSignVerifyToken()), FaultCode.INVALID_WEBHOOK_SIGN);
		AppHistory appHistory = appHistoryRepo.findByApp_AppIdAndCommitId(appPipe.getApp().getAppId(),
				payloadObj.getCommitId());
		as.isNotNull(appHistory, FaultCode.INVALID_APP_HISTORY);
		appHistory.setAssetId(payloadObj.getAssetId());
		appHistory.setAssetUrl(payloadObj.getAssetUrl());
		appHistory.setStatus(payloadObj.getStatus());
		appHistory.setTag(payloadObj.getTag());
		appHistory.setVersion(payloadObj.getVersion());
		appHistory.setTime(new Timestamp(System.currentTimeMillis()));
		appHistoryRepo.save(appHistory);
		rsp.setValue(SUCCESS);
		return new AppResponse(rsp, null);
	}
}