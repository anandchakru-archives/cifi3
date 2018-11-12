package com.anandchakru.app.repo;

import static com.anandchakru.app.repo.TestUtil.createNewApp;
import static com.anandchakru.app.repo.TestUtil.createNewAppNode;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.Charset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
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
	private final MediaType JSON_HAL = new MediaType("application", "hal+json", Charset.forName("UTF-8"));
	private final String APP_NAME = "app1";
	private final String APP_NODE_NAME = "node1";
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSave() throws JsonProcessingException, Exception {
		String app = createNewApp(mockMvc, APP_NAME); //app= http://localhost/repo/app/1
		String appNode = createNewAppNode(mockMvc, app, APP_NODE_NAME); //appNode=http://localhost/repo/app-node/1
		String appNodeApp = appNode + "/app"; //appNodeApp=http://localhost/repo/app-node/1/app
		mockMvc.perform(get(appNode)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$.name", is(APP_NODE_NAME)))
				.andExpect(jsonPath("$._links.app", hasEntry("href", appNodeApp)));
		mockMvc.perform(get(appNodeApp)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$.appName", is(APP_NAME)));
	}
}