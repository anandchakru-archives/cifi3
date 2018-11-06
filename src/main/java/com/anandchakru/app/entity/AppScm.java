package com.anandchakru.app.entity;

import java.io.Serializable;
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
import com.anandchakru.app.model.enums.ScmAgent;

@SuppressWarnings("serial")
@Entity
@Table(name = "app_scm")
public class AppScm implements Serializable {
	@Id
	@Column(name = "app_scm_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long appScmId;
	@JoinColumn(name = "appId", insertable = true, updatable = true)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private App app;
	@Enumerated(EnumType.STRING)
	private ScmAgent agent;
	private String url;
	@Column(name = "api_url")
	private String apiUrl;
	@Column(name = "api_token")
	private String apiToken;
	@Column(name = "sign_verify_token")
	private String signVerifyToken;

	public Long getAppScmId() {
		return appScmId;
	}
	public void setAppScmId(Long appScmId) {
		this.appScmId = appScmId;
	}
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	public ScmAgent getAgent() {
		return agent;
	}
	public void setAgent(ScmAgent agent) {
		this.agent = agent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public String getApiToken() {
		return apiToken;
	}
	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}
	public String getSignVerifyToken() {
		return signVerifyToken;
	}
	public void setSignVerifyToken(String signVerifyToken) {
		this.signVerifyToken = signVerifyToken;
	}
}
