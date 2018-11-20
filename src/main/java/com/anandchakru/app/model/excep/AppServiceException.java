package com.anandchakru.app.model.excep;

@SuppressWarnings("serial")
public class AppServiceException extends AbstractException {
	public AppServiceException(ExceptionMeta meta) {
		super(meta);
	}
}
