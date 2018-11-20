package com.anandchakru.app.controller;

import static com.anandchakru.app.model.constants.Cifi3.SUCCESS;
import static com.anandchakru.app.test.util.TestUtil.PIPE_PAYLOAD;
import static com.anandchakru.app.test.util.TestUtil.PIPE_PAYLOAD_SIGN;
import static com.anandchakru.app.test.util.TestUtil.SCM_PAYLOAD_HEAD;
import static com.anandchakru.app.test.util.TestUtil.SCM_PAYLOAD_HEAD_SIGN;
import static com.anandchakru.app.test.util.TestUtil.SCM_PAYLOAD_TAG;
import static com.anandchakru.app.test.util.TestUtil.SCM_PAYLOAD_TAG_SIGN;
import static com.anandchakru.app.test.util.TestUtil.addApp;
import static com.anandchakru.app.test.util.TestUtil.addAppHistory;
import static com.anandchakru.app.test.util.TestUtil.addAppNode;
import static com.anandchakru.app.test.util.TestUtil.addAppPipe;
import static com.anandchakru.app.test.util.TestUtil.addAppScm;
import static com.anandchakru.app.test.util.TestUtil.setupTestProfile;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class WebhookControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@BeforeClass
	public static void profile() {
		setupTestProfile();
	}
	@Test
	public void testScmWebhook1() throws Exception {
		//{"meta":{"details":{"INVALID_SCM_APP_ID":{"@c":"BasicFault","message":"Invalid Scm Id."}}}}
		mockMvc.perform(post("/webhook/cifi-scm").header("X-GitHub-Event", "push")
				.header("X-Hub-Signature", SCM_PAYLOAD_TAG_SIGN).content(SCM_PAYLOAD_TAG)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.meta.details", hasKey("INVALID_SCM_APP_ID")))
				.andExpect(jsonPath("$.meta.details.INVALID_SCM_APP_ID.@c", is("BasicFault")));
	}
	@Test
	public void testScmWebhook2() throws Exception {
		//{"response":{"@c":"WebhookRsp","value":"Ignoring refs/tags/v1.0.113, no match for BotBuildRegex:refs\\/heads\\/master"}}
		String appURI = addApp(mockMvc);
		addAppNode(mockMvc, appURI);
		addAppScm(mockMvc, appURI);
		addAppPipe(mockMvc, appURI);
		mockMvc.perform(post("/webhook/cifi-scm").header("X-GitHub-Event", "push")
				.header("X-Hub-Signature", SCM_PAYLOAD_TAG_SIGN).content(SCM_PAYLOAD_TAG)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.response.value", startsWith("Ignoring ")))
				.andExpect(jsonPath("$.response.@c", is("WebhookRsp")));
	}
	@Test
	public void testScmWebhook3() throws Exception {
		//{"response":{"@c":"WebhookRsp","value":"Success"}}
		String appURI = addApp(mockMvc);
		addAppNode(mockMvc, appURI);
		addAppScm(mockMvc, appURI);
		addAppPipe(mockMvc, appURI);
		//addAppHistory(mockMvc, appURI);
		mockMvc.perform(post("/webhook/cifi-scm").header("X-GitHub-Event", "push")
				.header("X-Hub-Signature", SCM_PAYLOAD_HEAD_SIGN).content(SCM_PAYLOAD_HEAD)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.response.value", is(SUCCESS)))
				.andExpect(jsonPath("$.response.@c", is("WebhookRsp")));
	}
	@Test
	public void testAcceptJenkins() throws Exception {
		//{"response":{"@c":"WebhookRsp","value":"Success"}}
		String appURI = addApp(mockMvc);
		addAppNode(mockMvc, appURI);
		addAppScm(mockMvc, appURI);
		addAppPipe(mockMvc, appURI);
		addAppHistory(mockMvc, appURI);
		mockMvc.perform(post("/webhook/cifi-pipe").header("X-Hub-Signature", PIPE_PAYLOAD_SIGN).content(PIPE_PAYLOAD))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.response.value", is(SUCCESS)))
				.andExpect(jsonPath("$.response.@c", is("WebhookRsp")));
	}
}
