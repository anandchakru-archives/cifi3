package com.anandchakru.app.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.anandchakru.app.model.excep.AbstractException;
import com.anandchakru.app.model.rsp.AppResponse;

@ControllerAdvice
public class ExceptionsHandler {
	@ExceptionHandler(AbstractException.class)
	public @ResponseBody AppResponse handleIllegalArguement(AbstractException ae) {
		return new AppResponse(null, ae.getMeta());
	}
}