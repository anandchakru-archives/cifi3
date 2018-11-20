package com.anandchakru.app.service;

import static com.anandchakru.app.model.constants.Cifi3.NO_SIGN;
import static com.anandchakru.app.test.util.TestUtil.setupTestProfile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SignVerifyServiceTest {
	private static final String LONG_TOKEN = "s8o2m5e6Ra8n89d3o2mA3n78d9R0ea43l675l67y567L56o78ng367S56i5g356n222a5624t45u456r2456e246Fo586r97E897x90t45r4a0S0a0fee563t634y6";
	private static final String LONG_SIGNATURE = "sha1=daf9ab5bfd30637f8f5ae992f05caea752cbca05";
	private SignVerifyService signService = new SignVerifyService();

	@BeforeClass
	public static void profile() {
		setupTestProfile();
	}
	@Test
	public void testVerify() {
		assertFalse("Fail", signService.verify("", "randomSignature", NO_SIGN));
		assertTrue("Fail", signService.verify("randomSource", "", NO_SIGN));
		assertTrue("Fail", signService.verify("randomSource", null, NO_SIGN));
		assertFalse("Fail", signService.verify("randomSource", null, null));
		assertFalse("Fail", signService.verify(null, null, null));
		assertFalse("Fail", signService.verify("randomSource", "randomSignature", null));
		assertTrue("Fail", signService.verify("randomSource", "randomSignature", NO_SIGN));
		assertFalse("Fail", signService.verify("randomSourceChanged", LONG_SIGNATURE, LONG_TOKEN));
		assertTrue("Fail", signService.verify("randomSource", LONG_SIGNATURE, LONG_TOKEN));
	}
	@Test
	public void testSign() {
		assertEquals("Fail", LONG_SIGNATURE, signService.sign("randomSource", LONG_TOKEN));
		assertNotEquals("Fail", LONG_SIGNATURE, signService.sign("randomSourceChanged", LONG_TOKEN));
	}
}
