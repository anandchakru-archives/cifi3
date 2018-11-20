package com.anandchakru.app.controller;

import static com.anandchakru.app.model.util.SampleDataUtil.HELLO_JTEST;
import static com.anandchakru.app.test.util.TestUtil.addApp;
import static com.anandchakru.app.test.util.TestUtil.addAppNode;
import static com.anandchakru.app.test.util.TestUtil.setupTestProfile;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.BeforeClass;
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

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Import({ Cifi3Config.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) //Clear DB after every method
public class ShutdownControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public static void profile() {
		setupTestProfile();
	}
	@Test
	public void testShutdownContext() throws Exception {
		//{"meta":{"details":{"INVLD_NODE_OR_APP":{"@c":"BasicFault","message":"Invalid App or Node."}}}}
		this.mockMvc.perform(get("/api/shutdown/1/2").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.meta.details", hasKey("INVLD_NODE_OR_APP")))
				.andExpect(jsonPath("$.meta.details.INVLD_NODE_OR_APP.@c", is("BasicFault")))
				.andExpect(jsonPath("$.meta.details.INVLD_NODE_OR_APP.message", is("Invalid App or Node.")));
	}
	@Test
	public void testShutdownContext2() throws Exception {
		//{"response":{"@c":"ScriptIoRsp","output":"hello from jtest\r\n","error":"","child":[]}}
		addAppNode(mockMvc, addApp(mockMvc));
		this.mockMvc.perform(get("/api/shutdown/1/1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.response.@c", is("ScriptIoRsp")))
				.andExpect(jsonPath("$.response.output", startsWith(HELLO_JTEST)));
	}
}