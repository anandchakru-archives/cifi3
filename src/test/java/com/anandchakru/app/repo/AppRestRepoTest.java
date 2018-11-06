package com.anandchakru.app.repo;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.anandchakru.app.config.Cifi3Config;
import com.anandchakru.app.entity.App;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Import({ Cifi3Config.class })
public class AppRestRepoTest {
	private final MediaType JSON_HAL = new MediaType("application", "hal+json", Charset.forName("UTF-8"));
	private ObjectMapper json = new ObjectMapper();
	private final String APP_NAME = "test1";
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testRoot() throws Exception {
		mockMvc.perform(get("/repo/")).andDo(print()).andExpect(status().isOk());
	}
	@Test
	public void testSave() throws Exception {
		App request = new App();
		request.setAppName(APP_NAME);
		String reqStr = json.writeValueAsString(request);
		this.mockMvc.perform(post("/repo/app").content(reqStr).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isCreated());
		//Find By Id
		this.mockMvc.perform(get("/repo/app/1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(JSON_HAL))
				.andExpect(jsonPath("$.appName", is(APP_NAME)));
		//Find All
		this.mockMvc.perform(get("/repo/app").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(JSON_HAL))
				.andExpect(jsonPath("$._embedded.apps[0].appName", is(APP_NAME)));
	}
}
