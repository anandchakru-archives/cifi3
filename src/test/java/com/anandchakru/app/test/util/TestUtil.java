package com.anandchakru.app.test.util;

import static com.anandchakru.app.model.constants.Cifi3.APP_HISTORY_REPO_URI;
import static com.anandchakru.app.model.constants.Cifi3.APP_NODE_REPO_URI;
import static com.anandchakru.app.model.constants.Cifi3.APP_PIPE_REPO_URI;
import static com.anandchakru.app.model.constants.Cifi3.APP_REPO_URI;
import static com.anandchakru.app.model.constants.Cifi3.APP_SCM_REPO_URI;
import static com.anandchakru.app.model.constants.Cifi3.APP_URI;
import static com.anandchakru.app.model.constants.Profile.TEST;
import static com.anandchakru.app.model.util.SampleDataUtil.TEXT_URI_LIST;
import static com.anandchakru.app.model.util.SampleDataUtil.makeApp;
import static com.anandchakru.app.model.util.SampleDataUtil.makeAppHistory;
import static com.anandchakru.app.model.util.SampleDataUtil.makeAppNode;
import static com.anandchakru.app.model.util.SampleDataUtil.makeAppPipe;
import static com.anandchakru.app.model.util.SampleDataUtil.makeAppScm;
import static org.springframework.core.env.AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;

public class TestUtil {
	public static final void setupTestProfile() {
		System.setProperty(DEFAULT_PROFILES_PROPERTY_NAME, TEST);
	}

	public static final String SCM_PAYLOAD_TAG_SIGN = "sha1=f5ace42b5be39c80674cf2afd0ea5b82239ef775";
	public static final String SCM_PAYLOAD_HEAD_SIGN = "sha1=7e7fab440cf4a5ef504ce52218f64ba9806e2431";//"sha1=312077f76f2e5d568ff466f986a25b0bc7e1fa87";
	public static final String PIPE_PAYLOAD_SIGN = "sha1=9a5ae8200aa12da570a59e61ee6fdd480c14720f";
	public static String SCM_PAYLOAD_HEAD = "";
	public static String SCM_PAYLOAD_TAG = "";
	public static String PIPE_PAYLOAD = "";
	static {
		try {
			SCM_PAYLOAD_HEAD = Resources.toString(Resources.getResource("ghhead.json"), StandardCharsets.UTF_8);
			SCM_PAYLOAD_TAG = Resources.toString(Resources.getResource("ghtag.json"), StandardCharsets.UTF_8);
			PIPE_PAYLOAD = Resources.toString(Resources.getResource("jen.json"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static ObjectMapper json = new ObjectMapper();

	/**
	 * Persist App
	 * 
	 * @param mockMvc
	 * @return
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	public static String addApp(final MockMvc mockMvc) throws JsonProcessingException, Exception {
		return mockMvc
				.perform(post(APP_REPO_URI).header(ACCEPT, APPLICATION_JSON).header(CONTENT_TYPE, APPLICATION_JSON)
						.content(json.writeValueAsString(makeApp())))
				.andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getHeader(LOCATION);
	}
	public static String addAppHistory(final MockMvc mockMvc, String appURI) throws JsonProcessingException, Exception {
		return linkAppChildToApp(mockMvc,
				mockMvc.perform(post(APP_HISTORY_REPO_URI).header(ACCEPT, APPLICATION_JSON)
						.header(CONTENT_TYPE, APPLICATION_JSON).content(json.writeValueAsString(makeAppHistory())))
						.andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getHeader(LOCATION),
				appURI);
	}
	public static String addAppNode(final MockMvc mockMvc, String appURI) throws JsonProcessingException, Exception {
		String loc = mockMvc
				.perform(post(APP_NODE_REPO_URI).header(ACCEPT, APPLICATION_JSON).header(CONTENT_TYPE, APPLICATION_JSON)
						.content(json.writeValueAsString(makeAppNode())))
				.andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getHeader(LOCATION);
		return linkAppChildToApp(mockMvc, loc, appURI);
	}
	public static String addAppScm(final MockMvc mockMvc, String appURI) throws JsonProcessingException, Exception {
		return linkAppChildToApp(mockMvc,
				mockMvc.perform(post(APP_SCM_REPO_URI).header(ACCEPT, APPLICATION_JSON)
						.header(CONTENT_TYPE, APPLICATION_JSON).content(json.writeValueAsString(makeAppScm())))
						.andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getHeader(LOCATION),
				appURI);
	}
	public static String addAppPipe(final MockMvc mockMvc, String appURI) throws JsonProcessingException, Exception {
		return linkAppChildToApp(mockMvc,
				mockMvc.perform(post(APP_PIPE_REPO_URI).header(ACCEPT, APPLICATION_JSON)
						.header(CONTENT_TYPE, APPLICATION_JSON).content(json.writeValueAsString(makeAppPipe())))
						.andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getHeader(LOCATION),
				appURI);
	}
	private static String linkAppChildToApp(final MockMvc mockMvc, String appChildURI, String appURI)
			throws JsonProcessingException, Exception {
		if (StringUtils.isNotEmpty(appChildURI) && StringUtils.isNotEmpty(appURI)) {
			mockMvc.perform(put(appChildURI + APP_URI).header(ACCEPT, APPLICATION_JSON)
					.header(CONTENT_TYPE, TEXT_URI_LIST).content(appURI)).andDo(print())
					.andExpect(status().isNoContent());
		}
		return appChildURI;
	}
}