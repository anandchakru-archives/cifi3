package com.anandchakru.app.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.anandchakru.app.model.rsp.AppResponse;
import com.anandchakru.app.service.ExecuteService;

@RestController
public class DeployController {
	@Autowired
	private ExecuteService executeService;

	@GetMapping("/api/deploy/{appHistoryId}")
	public @ResponseBody AppResponse deployApp(@PathVariable Long appHistoryId) {
		return new AppResponse(executeService.deploy(appHistoryId), null);
	}
	@GetMapping("/api/restart/{appId}/{appNodeId}")
	public @ResponseBody AppResponse restart(@PathVariable Long appId, @PathVariable Long appNodeId)
			throws InterruptedException, IOException {
		return new AppResponse(executeService.restart(appId, appNodeId), null);
	}
	@GetMapping("/api/shutdown/{appId}/{appNodeId}")
	public @ResponseBody AppResponse shutdown(@PathVariable Long appId, @PathVariable Long appNodeId)
			throws InterruptedException, IOException {
		return new AppResponse(executeService.shutdown(appId, appNodeId), null);
	}
}
