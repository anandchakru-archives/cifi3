package com.anandchakru.app.model.excep;

import java.io.Serializable;
import java.util.Map;
import com.anandchakru.app.model.enums.FaultCode;
import com.anandchakru.app.model.fault.Fault;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class ExceptionMeta implements Serializable {
	private Map<FaultCode, Fault> details = Maps.newHashMap();
	private String[] args;
}
