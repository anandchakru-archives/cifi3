package com.anandchakru.app.model.webhook.jenkins;

import java.io.Serializable;

@SuppressWarnings({ "serial" })
public class JenkinsCrumbResponse implements Serializable {
	private String crumb;
	private String crumbRequestField;

	public String getCrumb() {
		return crumb;
	}
	public void setCrumb(String crumb) {
		this.crumb = crumb;
	}
	public String getCrumbRequestField() {
		return crumbRequestField;
	}
	public void setCrumbRequestField(String crumbRequestField) {
		this.crumbRequestField = crumbRequestField;
	}
}
