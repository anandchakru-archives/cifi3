package com.anandchakru.app.controller;

import static com.anandchakru.app.repo.TestUtil.createNewApp;
import static com.anandchakru.app.repo.TestUtil.createNewAppNode;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.anandchakru.app.config.Cifi3Config;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Import({ Cifi3Config.class })
public class ShutdownControllerTest {
	private final String APP_NAME = "test1";
	private final String APP_NODE_NAME = "appNode1";
	@Autowired
	private MockMvc mockMvc;

	@Test
	@Ignore
	public void testShutdownContext() throws Exception {
		this.mockMvc.perform(get("/shutdown/1/1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.faults", hasKey("INVLD_NODE_OR_APP")))
				.andExpect(jsonPath("$.faults.INVLD_NODE_OR_APP.@c", is("BasicFault")))
				.andExpect(jsonPath("$.faults.INVLD_NODE_OR_APP.description", is("Invalid App or Node.")));
	}
	@Test
	public void testShutdownContext2() throws Exception {
		createNewApp(mockMvc, APP_NAME);
		createNewAppNode(mockMvc, APP_NAME, APP_NODE_NAME);
		this.mockMvc.perform(get("/shutdown/1/2").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.response", notNullValue()));
	}
}