package com.anandchakru.app.model.rsp;

@SuppressWarnings("serial")
public class HeartBeatRsp implements Response {
	private Long time;
	private String content;

	public HeartBeatRsp() {
		super();
	}
	public HeartBeatRsp(Long time, String content) {
		super();
		this.time = time;
		this.content = content;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "HeartBeatRsp [time=" + time + ", content=" + content + "]";
	}
}
