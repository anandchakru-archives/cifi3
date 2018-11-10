package com.anandchakru.app.service;

import static com.anandchakru.app.model.util.AppAssert.isNotEmpty;
import static com.anandchakru.app.model.util.AppAssert.isNotNull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anandchakru.app.entity.AppNode;
import com.anandchakru.app.model.enums.FaultCode;
import com.anandchakru.app.model.rsp.Response;
import com.anandchakru.app.model.rsp.ShutdownRsp;
import com.anandchakru.app.repo.AppNodeRestRepo;

@Service
public class ShutdownService {
	@Autowired
	private AppNodeRestRepo appNodeRepo;

	public Response startShutdownSequence(Long appId, Long appNodeId) throws InterruptedException, IOException {
		AppNode appNode = appNodeRepo.findByApp_appIdAndAppNodeId(appId, appNodeId);
		isNotNull(appNode, FaultCode.INVLD_NODE_OR_APP);
		isNotEmpty(appNode.getShutdownScript(), FaultCode.SHUTDWN_SCRPT_EMPTY);
		Process process = new ProcessBuilder().command(appNode.getShutdownScript()).start();
		return new ShutdownRsp(IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8),
				IOUtils.toString(process.getErrorStream(), StandardCharsets.UTF_8));
	}
}