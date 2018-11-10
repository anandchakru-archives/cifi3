package com.anandchakru.app.model.util;

import org.apache.commons.lang3.StringUtils;
import com.anandchakru.app.model.enums.FaultCode;
import com.anandchakru.app.model.fault.AppIllegalArgumentException;

public class AppAssert {
	public static void isTrue(Boolean value, FaultCode faultCode) throws AppIllegalArgumentException {
		if (!value) {
			throw new AppIllegalArgumentException(faultCode);
		}
	}
	public static void isNotEmpty(String value, FaultCode faultCode) throws AppIllegalArgumentException {
		if (StringUtils.isEmpty(value)) {
			throw new AppIllegalArgumentException(faultCode);
		}
	}
	public static String ifEmptyDefaultTo(String value, String defaultTo) {
		if (StringUtils.isEmpty(value)) {
			return defaultTo;
		}
		return value;
	}
	public static void isNotNull(Object value, FaultCode faultCode) throws AppIllegalArgumentException {
		if (value == null) {
			throw new AppIllegalArgumentException(faultCode);
		}
	}
}
