package com.anandchakru.app.service;

import static com.anandchakru.app.model.constants.Cifi3.SCRIPT_PATH;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.anandchakru.app.entity.AppNode;
import com.anandchakru.app.model.prop.CifiSettings;
import com.anandchakru.app.repo.AppNodeRestRepo;
import com.fasterxml.jackson.core.JsonProcessingException;

@Order(2)
@Service
public class PostInitRunner implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AppNodeRestRepo appNodeRepo;
	@Autowired
	private CifiSettings settings;

	@Override
	public void run(String... args) throws Exception {
		setupHostname();
		setupScripts();
	}
	private void setupScripts() throws IOException {
		AppNode appNode = appNodeRepo.findBySelfUrl(settings.getSelfurl());
		if (appNode == null) {
			return;
		}
		String stageDir = appNode.getStageDir();
		copyIfNotExistsInDir(appNode.getFetchScript(), stageDir);
		copyIfNotExistsInDir(appNode.getShutdownScript(), stageDir);
		copyIfNotExistsInDir(appNode.getStartScript(), stageDir);
	}
	private void copyIfNotExistsInDir(String fileName, String stageDir) throws IOException {
		ClassPathResource srcPathResource = new ClassPathResource(SCRIPT_PATH + fileName);
		if (srcPathResource != null) {
			try {
				File srcFile = srcPathResource.getFile();
				File destFile = new File(stageDir + File.separator + fileName);
				if (srcFile.exists() && !destFile.exists()) {
					logger.warn("Installing DEFAULT Script " + srcFile.getPath() + " to " + destFile.getPath());
					FileUtils.copyFile(srcFile, destFile);
					logger.debug("Installing DEFAULT Script complete.");
				}
			} catch (IOException e) {
				logger.error("FAIL: Installing DEFAULT Script " + fileName + " to " + stageDir);
			}
		}
	}
	/**
	 * Set hostname in settings after application initializes.
	 * @throws JsonProcessingException 
	 * 
	 */
	private void setupHostname() throws JsonProcessingException {
		if (StringUtils.isEmpty(settings.getSelfurl())) {
			settings.setSelfurl("http://localhost:9090/");
			logger.warn("You may override it with --cifi.selfurl=http://localhost:8989/");
		}
		logger.info("\n\n\n{}\n\n\n", settings.getSelfurl());
	}
}