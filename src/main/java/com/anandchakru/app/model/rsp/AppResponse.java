package com.anandchakru.app.model.rsp;

import java.io.Serializable;
import java.util.Map;
import com.anandchakru.app.model.fault.Fault;

@SuppressWarnings("serial")
public class AppResponse implements Serializable {
	private Map<String, Fault> faults;
	private Response response;

	public AppResponse() {
		super();
	}
	public AppResponse(Response response) {
		super();
		this.response = response;
	}
	public AppResponse(Map<String, Fault> faults, Response response) {
		super();
		this.faults = faults;
		this.response = response;
	}
	public Map<String, Fault> getFaults() {
		return faults;
	}
	public void setFaults(Map<String, Fault> faults) {
		this.faults = faults;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
}
