package com.anandchakru.app.repo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.apache.commons.lang3.StringUtils;
import org.springframework.test.web.servlet.MockMvc;
import com.anandchakru.app.entity.App;
import com.anandchakru.app.entity.AppNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	private static ObjectMapper json = new ObjectMapper();
	private static final String TEXT_URI_LIST = "text/uri-list";

	/**
	 * Save a new instance of App into DB
	 * 
	 * @param mockMvc
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	public static String createNewApp(MockMvc mockMvc, String appName) throws JsonProcessingException, Exception {
		App request = new App();
		request.setAppName(appName);
		String app = mockMvc.perform(post("/repo/app").content(json.writeValueAsString(request))).andDo(print())
				.andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
		return app;
	}
	public static String createNewAppNode(MockMvc mockMvc, String app, String appNodeName)
			throws JsonProcessingException, Exception {
		AppNode request = new AppNode();
		request.setCluster("cluster1");
		request.setIp("127.0.0.1");
		request.setLiveCommit("liveCommit");
		request.setName(appNodeName);
		request.setShutdownScript("shutdown.sh");
		request.setStartScript("startup.sh");
		String appNode = mockMvc.perform(post("/repo/app-node").content(json.writeValueAsString(request)))
				.andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
		if (StringUtils.isNotEmpty(app)) {
			mockMvc.perform(put(appNode + "/app").header("Content-Type", TEXT_URI_LIST).content(app)).andDo(print())
					.andExpect(status().isNoContent());
		}
		return appNode;
	}
}