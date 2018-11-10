package com.anandchakru.app.controller;

import static com.anandchakru.app.model.util.AppResponder.fault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.anandchakru.app.model.fault.AppIllegalArgumentException;
import com.anandchakru.app.model.rsp.AppResponse;
import com.anandchakru.app.service.FaultsService;

@ControllerAdvice
public class ExceptionController {
	@Autowired
	private FaultsService faultsService;

	@ExceptionHandler(AppIllegalArgumentException.class)
	public @ResponseBody AppResponse handleIllegalArguement(AppIllegalArgumentException appIllegalArgumentException) {
		return fault(faultsService.basic(appIllegalArgumentException.getFaultCodes()));
	}
}