package com.anandchakru.app.service;

import static com.anandchakru.app.test.util.TestUtil.setupTestProfile;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LoggerServiceTest {
	private LoggerService logger = new LoggerService();

	@BeforeClass
	public static void profile() {
		setupTestProfile();
	}
	@Test
	public void testParamAndArgsNull() {
		assertEquals(logger.paramAndArgs(null), "");
	}
	@Test
	public void testParamAndArgsOne() {
		assertEquals(logger.paramAndArgs(new Object[] { "aVal" }), "aVal");
	}
	@Test
	public void testParamAndArgsTwo() {
		assertEquals(logger.paramAndArgs(new Object[] { "aVal", "bVal" }), "aVal, bVal");
	}
}