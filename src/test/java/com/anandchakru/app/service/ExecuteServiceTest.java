package com.anandchakru.app.service;

import static com.anandchakru.app.model.constants.Cifi3.JSON_HAL;
import static com.anandchakru.app.model.util.SampleDataUtil.APP_NAME;
import static com.anandchakru.app.model.util.SampleDataUtil.APP_NODE_NAME;
import static com.anandchakru.app.model.util.SampleDataUtil.ASSET_ID;
import static com.anandchakru.app.model.util.SampleDataUtil.HELLO_JTEST;
import static com.anandchakru.app.test.util.TestUtil.addApp;
import static com.anandchakru.app.test.util.TestUtil.addAppHistory;
import static com.anandchakru.app.test.util.TestUtil.addAppNode;
import static com.anandchakru.app.test.util.TestUtil.addAppPipe;
import static com.anandchakru.app.test.util.TestUtil.addAppScm;
import static com.anandchakru.app.test.util.TestUtil.setupTestProfile;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.anandchakru.app.config.Cifi3Config;
import com.anandchakru.app.model.enums.ExecuteType;
import com.anandchakru.app.model.rsp.Response;
import com.anandchakru.app.model.rsp.ScriptIoRsp;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Import({ Cifi3Config.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) //Clear DB after every method
public class ExecuteServiceTest {
	@Autowired
	private MockMvc mockMvc;
	/*@MockBean
	private AppNodeRestRepo appNodeRepo;*/

	@BeforeClass
	public static void profile() {
		setupTestProfile();
	}

	@Autowired
	private ExecuteService executeService;

	@Test
	public void testDeploy() throws Exception {
		String appURI = addApp(mockMvc);
		String appNodeUri = addAppNode(mockMvc, appURI);
		addAppScm(mockMvc, appURI);
		addAppPipe(mockMvc, appURI);
		String historyUri = addAppHistory(mockMvc, appURI);
		String appNodeApp = appNodeUri + "/app"; //appNodeApp=http://localhost/repo/app-node/1/app
		mockMvc.perform(get(appNodeUri)).andDo(print()).andExpect(status().isOk()).andDo(print())
				.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$.nodeName", is(APP_NODE_NAME)))
				.andExpect(jsonPath("$._links.app", hasEntry("href", appNodeApp)));
		mockMvc.perform(get(appNodeApp)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$.appName", is(APP_NAME)));
		Long appHistoryId = Long.parseLong(historyUri.substring(historyUri.lastIndexOf("/") + 1));
		String appHistoryApp = historyUri + "/app"; //appNodeApp=http://localhost/repo/app-history/1/app
		mockMvc.perform(get(historyUri)).andDo(print()).andExpect(status().isOk()).andDo(print())
				.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$.assetId", is(ASSET_ID)))
				.andExpect(jsonPath("$._links.app", hasEntry("href", appHistoryApp)));
		mockMvc.perform(get(appHistoryApp)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$.appName", is(APP_NAME)));
		System.out.println(historyUri + ":" + executeService.deploy(appHistoryId));
	}
	@Test
	public void testShutdown() throws Exception {
		//when(appNodeRepo.findByApp_appIdAndAppNodeId(1l, 1l)).thenReturn(makeAppNode());
		String appURI = addApp(mockMvc);
		String appNodeUri = addAppNode(mockMvc, appURI);
		addAppScm(mockMvc, appURI);
		addAppPipe(mockMvc, appURI);
		Long appId = Long.parseLong(appURI.substring(appURI.lastIndexOf("/") + 1));
		Long appNodeId = Long.parseLong(appNodeUri.substring(appNodeUri.lastIndexOf("/") + 1));
		Response response = executeService.shutdown(appId, appNodeId);
		assertThat(response, instanceOf(ScriptIoRsp.class));
		ScriptIoRsp responseS = (ScriptIoRsp) response;
		assertThat(responseS.getOutput(), startsWith(HELLO_JTEST));
	}
	@Test
	public void testExecute() {
		//ensure no exception
		executeService.execute(ExecuteType.SHUTDOWN, new String[] { "powershell", "echo \"test\"" });
	}
}
