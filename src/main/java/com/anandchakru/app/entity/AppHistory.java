package com.anandchakru.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.anandchakru.app.model.enums.HistoryStatus;

@SuppressWarnings("serial")
@Entity
@Table(name = "app_history")
public class AppHistory implements Serializable {
	@Id
	@Column(name = "app_history_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long appHistoryId;
	@JoinColumn(name = "appId", insertable = true, updatable = true)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private App app;
	private String version;
	private String tag;
	private String commitId;
	@Enumerated(EnumType.STRING)
	private HistoryStatus status;
	@Column(name = "asset_url")
	private String assetUrl;
	@Column(name = "asset_id")
	private String assetId;
	private Timestamp time;

	public Long getAppHistoryId() {
		return appHistoryId;
	}
	public void setAppHistoryId(Long appHistoryId) {
		this.appHistoryId = appHistoryId;
	}
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
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
	public String getAssetUrl() {
		return assetUrl;
	}
	public void setAssetUrl(String assetUrl) {
		this.assetUrl = assetUrl;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}