package com.anandchakru.app.model.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.common.collect.ImmutableMap;

public enum FaultCode {
	UNXPTD_ERR, INVLD_NODE_OR_APP, INVLD_OPRTN, SHUTDOWN_SCRPT_EMPTY, START_SCRPT_EMPTY, INVLD_APP_NAME, INVLD_APP_NODE_NAME, INVALID_SCM_APP_ID, INVALID_PIPE_APP_ID, INVALID_APP_FOR_SCM, INVALID_APP_FOR_PIPE, INVALID_APP_PIPE_FOR_SCM, INVALID_WEBHOOK_SIGN, INVALID_JSON, INVALID_SCM_REF, PIPE_CRUMB_FAIL, PIPE_BUILD_FAIL, INVALID_APP_HISTORY, INVALID_APP_FOR_HISTORY, INVALID_APP_NODE_FOR_HISTORY, INVALID_APP_SCM_FOR_HISTORY, IO_WHILE_EXECUTE;
	private static final Map<String, FaultCode> _map = ImmutableMap
			.copyOf(Arrays.asList(FaultCode.values()).stream().collect(Collectors.toMap(k -> k.toString(), v -> v)));

	public static FaultCode find(final String fault) {
		FaultCode val = _map.get(fault);
		if (val == null) {
			return FaultCode.UNXPTD_ERR;
		}
		return val;
	}
}
