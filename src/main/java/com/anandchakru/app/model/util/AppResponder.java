package com.anandchakru.app.model.util;

import java.util.Map;
import com.anandchakru.app.model.fault.Fault;
import com.anandchakru.app.model.rsp.AppResponse;
import com.anandchakru.app.model.rsp.Response;

public class AppResponder {
	public static AppResponse respond(Response response) {
		return new AppResponse(response);
	}
	public static AppResponse fault(Map<String, Fault> faults) {
		return new AppResponse(faults, null);
	}
	public static AppResponse partial(Response response, Map<String, Fault> faults) {
		return new AppResponse(faults, response);
	}
}
