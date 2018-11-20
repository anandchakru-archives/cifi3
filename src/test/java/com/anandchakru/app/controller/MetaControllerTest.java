package com.anandchakru.app.controller;

import static com.anandchakru.app.test.util.TestUtil.setupTestProfile;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
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
public class MetaControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public static void profile() {
		setupTestProfile();
	}
	@Test
	public void testHb() throws Exception {
		//1542520272911: Heartbeat
		this.mockMvc.perform(get("/hb").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(endsWith("Heartbeat")));
	}
	@Test
	public void testHbj() throws Exception {
		//{"response":{"@c":"HeartBeatRsp","time":1542520272770,"content":"Heartbeat"}}
		this.mockMvc.perform(get("/hbj").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.response.content", isA(String.class)))
				.andExpect(jsonPath("$.response.@c", is("HeartBeatRsp")))
				.andExpect(jsonPath("$.response.content", is("Heartbeat")));
	}
}