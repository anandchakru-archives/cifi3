package com.anandchakru.app.model.webhook.jenkins;

import java.io.Serializable;
import com.anandchakru.app.model.enums.HistoryStatus;

@SuppressWarnings("serial")
public class JenkinsJobPayload implements Serializable {
	private HistoryStatus status;
	private Long appId;
	private String commitId;
	private String assetId;
	private String version;
	private String tag;
	private String assetUrl;

	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public HistoryStatus getStatus() {
		return status;
	}
	public void setStatus(HistoryStatus status) {
		this.status = status;
	}
	public String getCommitId() {
		return commitId;
	}
	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getAssetUrl() {
		return assetUrl;
	}
	public void setAssetUrl(String assetUrl) {
		this.assetUrl = assetUrl;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
