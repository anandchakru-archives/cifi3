package com.anandchakru.app.model.rsp;

@SuppressWarnings("serial")
public class ShutdownRsp implements Response {
	private String output;
	private String error;

	public ShutdownRsp() {
		super();
	}
	public ShutdownRsp(String output, String error) {
		super();
		this.output = output;
		this.error = error;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
