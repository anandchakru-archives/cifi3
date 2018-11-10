package com.anandchakru.app.repo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;
import com.anandchakru.app.entity.App;
import com.anandchakru.app.entity.AppNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	private static ObjectMapper json = new ObjectMapper();

	/**
	 * Save a new instance of App into DB
	 * 
	 * @param mockMvc
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	public static void createNewApp(MockMvc mockMvc, String appName) throws JsonProcessingException, Exception {
		App request = new App();
		request.setAppName(appName);
		mockMvc.perform(post("/repo/app").content(json.writeValueAsString(request))).andDo(print())
				.andExpect(status().isCreated());
	}
	public static void createNewAppNode(MockMvc mockMvc, String appName, String appNodeName)
			throws JsonProcessingException, Exception {
		AppNode request = new AppNode();
		App app = new App();
		app.setAppName(appName);
		request.setApp(app);
		request.setCluster("cluster1");
		request.setIp("127.0.0.1");
		request.setLiveCommit("liveCommit");
		request.setName(appNodeName);
		request.setShutdownScript("shutdown.sh");
		request.setStartScript("startup.sh");
		mockMvc.perform(post("/repo/app-node").content(json.writeValueAsString(request))).andDo(print())
				.andExpect(status().isCreated());
	}
	public static void createNewAppNode(MockMvc mockMvc, Long appId, String appNodeName)
			throws JsonProcessingException, Exception {
		AppNode request = new AppNode();
		App app = new App();
		app.setAppId(appId);
		request.setApp(app);
		request.setCluster("cluster1");
		request.setIp("127.0.0.1");
		request.setLiveCommit("liveCommit");
		request.setName(appNodeName);
		request.setShutdownScript("shutdown.sh");
		request.setStartScript("startup.sh");
		mockMvc.perform(post("/repo/app-node").content(json.writeValueAsString(request))).andDo(print())
				.andExpect(status().isCreated());
	}
}