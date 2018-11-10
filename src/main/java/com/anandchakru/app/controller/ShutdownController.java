package com.anandchakru.app.controller;

import static com.anandchakru.app.model.util.AppResponder.respond;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.anandchakru.app.model.rsp.AppResponse;
import com.anandchakru.app.service.ShutdownService;

@RestController
public class ShutdownController {
	@Autowired
	private ShutdownService shutdownService;

	@RequestMapping(value = "/shutdown/{appId}/{appNodeId}", method = RequestMethod.GET)
	public @ResponseBody AppResponse shutdown(@PathVariable Long appId, @PathVariable Long appNodeId)
			throws InterruptedException, IOException {
		return respond(shutdownService.startShutdownSequence(appId, appNodeId));
	}
}