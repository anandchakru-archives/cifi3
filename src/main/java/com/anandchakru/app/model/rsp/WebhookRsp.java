package com.anandchakru.app.model.rsp;

@SuppressWarnings("serial")
public class WebhookRsp implements Response {
	private String value;

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "WebhookRsp [value=" + value + "]";
	}
}