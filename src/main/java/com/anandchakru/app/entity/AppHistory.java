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
import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@ToString
@Entity
@Table(name = "app_history")
public class AppHistory implements Serializable {
	@Id
	@Column(name = "app_history_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appHistoryId;
	@JoinColumn(name = "appId", insertable = true, updatable = true)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private App app;
	@Column(name = "version", length = 32)
	private String version;
	@Column(name = "tag", length = 64)
	private String tag;
	@Column(name = "commit_id", length = 64)
	private String commitId;
	@Column(name = "status", length = 32)
	@Enumerated(EnumType.STRING)
	private HistoryStatus status;
	@Column(name = "asset_url", length = 1024)
	private String assetUrl;
	@Column(name = "asset_id", length = 64)
	private String assetId;
	private Timestamp time;
}