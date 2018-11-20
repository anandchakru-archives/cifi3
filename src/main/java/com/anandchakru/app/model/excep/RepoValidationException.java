package com.anandchakru.app.model.excep;

@SuppressWarnings("serial")
public class RepoValidationException extends AbstractException {
	public RepoValidationException(ExceptionMeta meta) {
		super(meta);
	}
}
