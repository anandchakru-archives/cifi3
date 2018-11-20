package com.anandchakru.app.model.webhook.github;

import java.io.Serializable;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.event.PushPayload;

@SuppressWarnings("serial")
public class WebhookPushPayLoad extends PushPayload implements Serializable {
	private String after;
	private Repository repository;
	private WebhookCommit head_commit;

	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
	}
	public Repository getRepository() {
		return repository;
	}
	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	public WebhookCommit getHead_commit() {
		return head_commit;
	}
	public void setHead_commit(WebhookCommit head_commit) {
		this.head_commit = head_commit;
	}
}
