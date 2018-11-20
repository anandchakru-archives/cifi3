package com.anandchakru.app.repo;

import static com.anandchakru.app.model.util.SampleDataUtil.APP_NAME;
import static com.anandchakru.app.test.util.TestUtil.addApp;
import static com.anandchakru.app.test.util.TestUtil.setupTestProfile;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.Charset;
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

/**
 * https://stackoverflow.com/a/53145466/234110
 *
 * @author anand
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Import({ Cifi3Config.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) //Clear DB after every method
public class AppRepoTest {
	private final MediaType JSON_HAL = new MediaType("application", "hal+json", Charset.forName("UTF-8"));
	@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public static void profile() {
		setupTestProfile();
	}
	@Test
	public void testFindById() throws Exception {
		addApp(mockMvc);
		mockMvc.perform(get("/repo/app")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(JSON_HAL))
				.andExpect(jsonPath("$._embedded.apps[0].appName", is(APP_NAME)));
	}
	@Test
	public void testFindAll() throws Exception {
		//Size should be 0
		mockMvc.perform(get("/repo/app")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$._embedded.apps", hasSize(0)));
		addApp(mockMvc);
		//Size should be 1
		mockMvc.perform(get("/repo/app")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$._embedded.apps", hasSize(1)));
	}
	@Test
	public void testFindByName() throws Exception {
		mockMvc.perform(get("/repo/app/search/by?appName=cifi3")).andDo(print()).andExpect(status().isOk());
	}
}
