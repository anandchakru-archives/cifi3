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
import com.anandchakru.app.model.enums.PipeAgent;

@SuppressWarnings("serial")
@Entity
@Table(name = "app_pipe")
public class AppPipe implements Serializable {
	@Id
	@Column(name = "app_pipe_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "hs_app_pipe")
	private Long appPipeId;
	@JoinColumn(name = "appId", insertable = true, updatable = true)
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private App app;
	@Enumerated(EnumType.STRING)
	private PipeAgent status;
	private String url;
	private String name;
	private String user;
	@Column(name = "api_token")
	private String apiToken;
	@Column(name = "build_trigger_token")
	private String buildTriggerToken;
	@Column(name = "sign_verify_token")
	private String signVerifyToken;
	@Column(name = "bot_build")
	private Boolean botBuild;
	@Column(name = "bot_build_regex")
	private String botBuildRegex;
	@Column(name = "bot_deploy")
	private Boolean botDeploy;
	@Column(name = "bot_deploy_condition")
	private String botDeployCondition;

	public Long getAppPipeId() {
		return appPipeId;
	}
	public void setAppPipeId(Long appPipeId) {
		this.appPipeId = appPipeId;
	}
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	public PipeAgent getStatus() {
		return status;
	}
	public void setStatus(PipeAgent status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getApiToken() {
		return apiToken;
	}
	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}
	public String getBuildTriggerToken() {
		return buildTriggerToken;
	}
	public void setBuildTriggerToken(String buildTriggerToken) {
		this.buildTriggerToken = buildTriggerToken;
	}
	public String getSignVerifyToken() {
		return signVerifyToken;
	}
	public void setSignVerifyToken(String signVerifyToken) {
		this.signVerifyToken = signVerifyToken;
	}
	public Boolean getBotBuild() {
		return botBuild;
	}
	public void setBotBuild(Boolean botBuild) {
		this.botBuild = botBuild;
	}
	public String getBotBuildRegex() {
		return botBuildRegex;
	}
	public void setBotBuildRegex(String botBuildRegex) {
		this.botBuildRegex = botBuildRegex;
	}
	public Boolean getBotDeploy() {
		return botDeploy;
	}
	public void setBotDeploy(Boolean botDeploy) {
		this.botDeploy = botDeploy;
	}
	public String getBotDeployCondition() {
		return botDeployCondition;
	}
	public void setBotDeployCondition(String botDeployCondition) {
		this.botDeployCondition = botDeployCondition;
	}
}
