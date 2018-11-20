package com.anandchakru.app.model.webhook.github;

import org.eclipse.egit.github.core.Commit;

@SuppressWarnings("serial")
public class WebhookCommit extends Commit {
	private String id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}