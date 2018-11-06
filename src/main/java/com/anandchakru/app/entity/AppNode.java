package com.anandchakru.app.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "app_node")
public class AppNode implements Serializable {
	@Id
	@Column(name = "app_node_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long appNodeId;
	@JoinColumn(name = "appId", insertable = true, updatable = true)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private App app;
	private String name;
	private String ip;
	private String cluster;
	@Column(name = "live_commit")
	private String liveCommit;
	@Column(name = "start_script")
	private String startScript;
	@Column(name = "shutdown_script")
	private String shutdownScript;

	public Long getAppNodeId() {
		return appNodeId;
	}
	public void setAppNodeId(Long appNodeId) {
		this.appNodeId = appNodeId;
	}
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCluster() {
		return cluster;
	}
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
	public String getLiveCommit() {
		return liveCommit;
	}
	public void setLiveCommit(String liveCommit) {
		this.liveCommit = liveCommit;
	}
	public String getStartScript() {
		return startScript;
	}
	public void setStartScript(String startScript) {
		this.startScript = startScript;
	}
	public String getShutdownScript() {
		return shutdownScript;
	}
	public void setShutdownScript(String shutdownScript) {
		this.shutdownScript = shutdownScript;
	}
}