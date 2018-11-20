package com.anandchakru.app.repo;

import static com.anandchakru.app.model.constants.Cifi3.JSON_HAL;
import static com.anandchakru.app.model.util.SampleDataUtil.APP_NAME;
import static com.anandchakru.app.model.util.SampleDataUtil.APP_NODE_NAME;
import static com.anandchakru.app.test.util.TestUtil.addApp;
import static com.anandchakru.app.test.util.TestUtil.addAppNode;
import static com.anandchakru.app.test.util.TestUtil.setupTestProfile;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
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
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * curl -is -d '{"name":"appNode4","ip":"127.0.0.1","cluster":"cluster1","liveCommit":"liveCommit","startScript":"startup.sh","shutdownScript":"shutdown.sh","heartbeatUrl":"somehburlfoo"}' -H "Content-Type: application/json" -X POST http://localhost:9090/repo/app-node
 * curl -is -d '{"appName":"test1"}' -H "Content-Type: application/json" -X POST http://localhost:9090/repo/app
 * curl -is -d 'repo/app/1' -H "Content-Type: text/uri-list;charset=UTF-8" -X PUT http://localhost:9090/repo/app-node/1/app
 * curl -is -X GET http://localhost:9090/repo/app-node/1/app
 * 
 * @author anand
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Import({ Cifi3Config.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) //Clear DB after every method
public class AppNodeRepoTest {
	@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public static void profile() {
		setupTestProfile();
	}
	@Test
	public void testSave() throws JsonProcessingException, Exception {
		String appNode = addAppNode(mockMvc, addApp(mockMvc));//addApp= http://localhost/repo/app/1 && addAppNode=http://localhost/repo/app-node/1
		String appNodeApp = appNode + "/app"; //appNodeApp=http://localhost/repo/app-node/1/app
		mockMvc.perform(get(appNode)).andDo(print()).andExpect(status().isOk()).andDo(print())
				.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$.nodeName", is(APP_NODE_NAME)))
				.andExpect(jsonPath("$._links.app", hasEntry("href", appNodeApp)));
		mockMvc.perform(get(appNodeApp)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$.appName", is(APP_NAME)));
	}
}