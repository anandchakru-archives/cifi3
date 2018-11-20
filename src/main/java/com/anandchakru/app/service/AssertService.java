package com.anandchakru.app.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anandchakru.app.model.enums.FaultCode;
import com.anandchakru.app.model.excep.AppIllegalArgumentException;
import com.anandchakru.app.model.excep.ExceptionMeta;
import com.anandchakru.app.model.fault.BasicFault;
import com.anandchakru.app.model.fault.Fault;
import com.anandchakru.app.model.prop.CifiSettings;
import com.google.common.collect.ImmutableMap;

@Service
public class AssertService {
	@Autowired
	private CifiSettings settings;

	public void isTrue(Boolean value, FaultCode faultCode) throws AppIllegalArgumentException {
		if (!value) {
			throw new AppIllegalArgumentException(new ExceptionMeta(codeToFault(faultCode), null));
		}
	}
	public void isNotEmpty(String value, FaultCode faultCode) throws AppIllegalArgumentException {
		if (StringUtils.isEmpty(value)) {
			throw new AppIllegalArgumentException(new ExceptionMeta(codeToFault(faultCode), null));
		}
	}
	public ImmutableMap<FaultCode, Fault> codeToFault(FaultCode fc) {
		return ImmutableMap.<FaultCode, Fault>builder().put(fc, new BasicFault(settings.getFaults().get(fc.toString())))
				.build();
	}
	public void isNotNull(Object value, FaultCode faultCode) throws AppIllegalArgumentException {
		if (value == null) {
			throw new AppIllegalArgumentException(new ExceptionMeta(codeToFault(faultCode), null));
		}
	}
	public void isNull(Object value, FaultCode faultCode) throws AppIllegalArgumentException {
		if (value != null) {
			throw new AppIllegalArgumentException(new ExceptionMeta(codeToFault(faultCode), null));
		}
	}
	public String ifNullDefaultTo(String value, String defaultValue) {
		if (StringUtils.isEmpty(value)) {
			return defaultValue;
		}
		return value;
	}
}
