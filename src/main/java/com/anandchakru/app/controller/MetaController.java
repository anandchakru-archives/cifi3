package com.anandchakru.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.anandchakru.app.model.rsp.AppResponse;
import com.anandchakru.app.model.rsp.HeartBeatRsp;

@RestController
public class MetaController {
	@GetMapping("/hb")
	public String hb() {
		return System.currentTimeMillis() + ": Heartbeat";
	}
	@GetMapping("/hbj")
	public AppResponse hbJson() {
		return new AppResponse(new HeartBeatRsp(System.currentTimeMillis(), "Heartbeat"), null);
	}
}
