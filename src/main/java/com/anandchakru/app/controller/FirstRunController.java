package com.anandchakru.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.anandchakru.app.model.prop.CifiSettings;
import com.anandchakru.app.model.rsp.AppResponse;
import com.anandchakru.app.model.rsp.FirstRunHomeRsp;

@RestController
public class FirstRunController {
	@Autowired
	private CifiSettings settings;

	/**
	 * Responds First Run Information
	 * 
	 * @return
	 */
	@GetMapping(path = "/api/firstrun/home")
	public @ResponseBody AppResponse firstRunHome() {
		FirstRunHomeRsp firstRunHomeRsp = new FirstRunHomeRsp();
		firstRunHomeRsp.setAppname(settings.getAppname());
		return new AppResponse(firstRunHomeRsp, null);
	}
}