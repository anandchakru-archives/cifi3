package com.anandchakru.app.repo.eventhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import com.anandchakru.app.entity.App;
import com.anandchakru.app.entity.AppHistory;
import com.anandchakru.app.entity.AppNode;
import com.anandchakru.app.entity.AppPipe;
import com.anandchakru.app.entity.AppScm;

@Service
@RepositoryEventHandler
public class RepoEventHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//
	//	After Save/Update
	//
	@HandleAfterSave
	@HandleAfterCreate
	public void afterApp(App app) {
		log("Saved", app);
	}
	@HandleAfterSave
	@HandleAfterCreate
	public void afterAppNode(AppNode appNode) {
		log("Saved", appNode);
	}
	@HandleAfterSave
	@HandleAfterCreate
	public void afterAppPipe(AppPipe appPipe) {
		log("Saved", appPipe);
	}
	@HandleAfterSave
	@HandleAfterCreate
	public void afterAppScm(AppScm appScm) {
		log("Saved", appScm);
	}
	@HandleAfterSave
	@HandleAfterCreate
	public void afterAppHistory(AppHistory appHistory) {
		log("Saved", appHistory);
	}
	//
	//	After Delete
	//
	@HandleAfterDelete
	public void afterAppDelete(App app) {
		log("Deleted", app);
	}
	@HandleAfterDelete
	public void afterAppNodeDelete(AppNode appNode) {
		log("Deleted", appNode);
	}
	@HandleAfterDelete
	public void afterAppPipeDelete(AppPipe appPipe) {
		log("Deleted", appPipe);
	}
	@HandleAfterDelete
	public void afterAppScmDelete(AppScm appScm) {
		log("Deleted", appScm);
	}
	@HandleAfterDelete
	public void afterAppHistoryDelete(AppHistory appHistory) {
		log("Deleted", appHistory);
	}
	private void log(String saveOrDelete, Object entity) {
		logger.trace("{} {}", saveOrDelete, entity);
	}
}