package com.anandchakru.app.repo.validators;

import static com.anandchakru.app.model.constants.Field.APP_NAME_MAX_LEN;
import static com.anandchakru.app.model.constants.Field.APP_NODE_NAME_MAX_LEN;
import static com.anandchakru.app.model.enums.FaultCode.INVLD_APP_NAME;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import com.anandchakru.app.entity.App;
import com.anandchakru.app.entity.AppHistory;
import com.anandchakru.app.entity.AppNode;
import com.anandchakru.app.entity.AppPipe;
import com.anandchakru.app.entity.AppScm;
import com.anandchakru.app.model.excep.ExceptionMeta;
import com.anandchakru.app.model.excep.RepoValidationException;
import com.anandchakru.app.service.AssertService;

@Service
@RepositoryEventHandler
public class AppValidator {
	@Autowired
	private AssertService as;

	@HandleBeforeCreate //POST
	@HandleBeforeSave //PUT | PATCH
	public void validate(App app) {
		if (StringUtils.isEmpty(app.getAppName()) || app.getAppName().length() > APP_NAME_MAX_LEN) {
			throw new RepoValidationException(new ExceptionMeta(as.codeToFault(INVLD_APP_NAME),
					new String[] { String.valueOf(APP_NAME_MAX_LEN) }));
		}
	}
	@HandleBeforeCreate
	@HandleBeforeSave
	public void validate(AppNode appNode) {
		if (StringUtils.isEmpty(appNode.getNodeName()) || appNode.getNodeName().length() > APP_NODE_NAME_MAX_LEN) {
			throw new RepoValidationException(new ExceptionMeta(as.codeToFault(INVLD_APP_NAME),
					new String[] { String.valueOf(APP_NODE_NAME_MAX_LEN) }));
		}
	}
	@HandleBeforeCreate
	@HandleBeforeSave
	public void validate(AppHistory appHistory) {
		//TODO: Validate AppHistory
	}
	@HandleBeforeCreate
	@HandleBeforeSave
	public void validate(AppScm appScm) {
		//TODO: Validate AppScm
	}
	@HandleBeforeCreate
	@HandleBeforeSave
	public void validate(AppPipe appPipe) {
		//TODO: Validate AppPipe
	}
}