package com.anandchakru.app.service;

import static com.anandchakru.app.model.util.AppAssert.ifEmptyDefaultTo;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anandchakru.app.model.enums.FaultCode;
import com.anandchakru.app.model.fault.BasicFault;
import com.anandchakru.app.model.fault.Fault;
import com.anandchakru.app.model.prop.CifiSettings;

@Service
public class FaultsService {
	public static final String FAULT_NOT_FOUND = "_NOT_FOUND";
	@Autowired
	private CifiSettings cifiSettings;

	public Map<String, Fault> basic(List<FaultCode> keys) {
		return basic(keys.stream().toArray(FaultCode[]::new));
	}
	public Map<String, Fault> basic(FaultCode... keys) {
		return Arrays.stream(keys).collect(Collectors.toMap(key -> key.toString(), key -> new BasicFault(
				ifEmptyDefaultTo(cifiSettings.getFaults().get(key.toString()), key + FAULT_NOT_FOUND))));
	}
}