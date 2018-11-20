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
import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Data
@ToString
@Table(name = "app_scm")
public class AppScm implements Serializable {
	@Id
	@Column(name = "app_scm_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appScmId;
	@JoinColumn(name = "appId", insertable = true, updatable = true)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private App app;
	@Enumerated(EnumType.STRING)
	@Column(name = "agent", length = 64)
	private ScmAgent agent;
	@Column(name = "url", length = 1024)
	private String url;
	@Column(name = "api_url", length = 1024)
	private String apiUrl;
	@Column(name = "api_token", length = 512)
	private String apiToken;
	@Column(name = "sign_verify_token", length = 2048)
	private String signVerifyToken;
	@Column(name = "sign_verify_header", length = 128)
	private String signVerifyHeader;
	@Column(name = "event_header", length = 128)
	private String eventHeader;
	@Column(name = "scm_app_id", length = 32)
	private String scmAppId;
}
