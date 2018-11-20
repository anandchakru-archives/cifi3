package com.anandchakru.app.service;

import static com.anandchakru.app.model.constants.Cifi3.SUCCESS;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.anandchakru.app.entity.AppPipe;
import com.anandchakru.app.model.enums.FaultCode;
import com.anandchakru.app.model.webhook.jenkins.JenkinsCrumbResponse;

@Service
public class PipeService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RestTemplate rest;
	@Autowired
	private AssertService as;

	public String build(AppPipe pipe, String cause) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", basicAuth(pipe));
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<JenkinsCrumbResponse> crumb = rest.exchange(pipe.getUrl() + "/crumbIssuer/api/json",
				HttpMethod.GET, request, JenkinsCrumbResponse.class); // http://192.168.1.7:8080/crumbIssuer/api/json
		as.isTrue(crumb.getStatusCode().equals(HttpStatus.OK) && crumb.getBody() != null, FaultCode.PIPE_CRUMB_FAIL);
		headers.add(crumb.getBody().getCrumbRequestField(), crumb.getBody().getCrumb());
		ResponseEntity<String> buildRsp = rest.exchange(
				pipe.getUrl() + "/job/cifi3/build?token=" + pipe.getBuildTriggerToken() + "&cause=" + cause,
				HttpMethod.POST, request, String.class);
		as.isTrue(buildRsp.getStatusCode().equals(HttpStatus.CREATED), FaultCode.PIPE_BUILD_FAIL);
		logger.warn(buildRsp.getBody());
		return SUCCESS;
	}
	private String basicAuth(AppPipe pipe) {
		return "Basic " + new String(Base64.encodeBase64((pipe.getUser() + ":" + pipe.getApiToken()).getBytes()));
	}
}