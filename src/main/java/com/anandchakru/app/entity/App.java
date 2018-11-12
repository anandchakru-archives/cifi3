package com.anandchakru.app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "app")
public class App implements Serializable {
	@Id
	@Column(name = "app_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "hs_app")
	private Long appId;
	@Column(name = "app_name")
	private String appName;

	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
}