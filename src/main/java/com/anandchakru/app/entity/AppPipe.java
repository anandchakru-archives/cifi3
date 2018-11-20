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
import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Data
@ToString
@Table(name = "app_pipe")
public class AppPipe implements Serializable {
	@Id
	@Column(name = "app_pipe_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appPipeId;
	@JoinColumn(name = "appId", insertable = true, updatable = true)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private App app;
	@Column(name = "agent", length = 36)
	@Enumerated(EnumType.STRING)
	private PipeAgent agent;
	@Column(name = "url", length = 2048)
	private String url;
	@Column(name = "name", length = 64)
	private String name;
	@Column(name = "user", length = 64)
	private String user;
	@Column(name = "api_token", length = 2048)
	private String apiToken;
	@Column(name = "build_trigger_token", length = 2048)
	private String buildTriggerToken;
	@Column(name = "sign_verify_token", length = 2048)
	private String signVerifyToken;
	@Column(name = "sign_verify_header", length = 2048)
	private String signVerifyHeader;
	@Column(name = "bot_build")
	private Boolean botBuild;
	@Column(name = "bot_build_regex", length = 2048)
	private String botBuildRegex;
	@Column(name = "bot_deploy")
	private Boolean botDeploy;
	@Column(name = "bot_deploy_condition", length = 2048)
	private String botDeployCondition;
}
