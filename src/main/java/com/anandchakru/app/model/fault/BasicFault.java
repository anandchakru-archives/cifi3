package com.anandchakru.app.model.fault;

import static com.anandchakru.app.model.constants.Cifi3.NO_DESCRIPTION;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@SuppressWarnings("serial")
public class BasicFault implements Fault {
	private String message;

	public BasicFault(String message) {
		super();
		this.message = message == null ? NO_DESCRIPTION : message;
	}
}