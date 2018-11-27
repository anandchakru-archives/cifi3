package com.anandchakru.app.entity;

import static com.anandchakru.app.model.constants.Field.APP_NAME_MAX_LEN;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@ToString
@Entity
@Table(name = "app")
public class App implements Serializable {
	@Id
	@Column(name = "app_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appId;
	@Column(name = "app_name", length = APP_NAME_MAX_LEN)
	private String appName;
	@ToString.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "app", cascade = CascadeType.REFRESH)
	private List<AppNode> nodes = Lists.newArrayList();
	@ToString.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "app", cascade = CascadeType.REFRESH)
	private List<AppPipe> pipes = Lists.newArrayList();
	@ToString.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "app", cascade = CascadeType.REFRESH)
	private List<AppHistory> history = Lists.newArrayList();
	@ToString.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "app", cascade = CascadeType.REFRESH)
	private List<AppScm> scm = Lists.newArrayList();

	public App() {
		super();
	}
	public App(String appName) {
		super();
		this.appName = appName;
	}
	public App(Long appId, String appName) {
		super();
		this.appId = appId;
		this.appName = appName;
	}
}