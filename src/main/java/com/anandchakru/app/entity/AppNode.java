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
import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@ToString
@Entity
@Table(name = "app_node")
public class AppNode implements Serializable {
	@Id
	@Column(name = "app_node_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appNodeId;
	@JoinColumn(name = "appId", insertable = true, updatable = true)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private App app;
	@Column(name = "node_name", length = 64)
	private String nodeName;
	@Column(name = "ip", length = 64)
	private String ip;
	@Column(name = "cluster", length = 64)
	private String cluster;
	@Column(name = "arti_file", length = 64)
	private String artiFileName;
	@Column(name = "fetch_script", length = 1024)
	private String fetchScript;
	@Column(name = "shutdown_script", length = 1024)
	private String shutdownScript;
	@Column(name = "start_script", length = 1024)
	private String startScript;
	@Column(name = "heartbeat_url", length = 1024)
	private String heartbeatUrl;
	@Column(name = "stage_dir", length = 1024)
	private String stageDir;
	@Column(name = "self_url", length = 128)
	private String selfUrl;
	@Column(name = "spring_profiles", length = 512)
	private String springProfiles;
}