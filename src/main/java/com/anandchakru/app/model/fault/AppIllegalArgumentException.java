package com.anandchakru.app.model.fault;

import java.util.Collections;
import java.util.List;
import com.anandchakru.app.model.enums.FaultCode;
import com.google.common.collect.Lists;

@SuppressWarnings("serial")
public class AppIllegalArgumentException extends IllegalArgumentException {
	private List<FaultCode> faultCodes;

	public AppIllegalArgumentException(FaultCode... faultCodes) {
		super();
		this.faultCodes = Lists.newArrayList(faultCodes);
	}
	public List<FaultCode> getFaultCodes() {
		return Collections.unmodifiableList(faultCodes);
	}
}
