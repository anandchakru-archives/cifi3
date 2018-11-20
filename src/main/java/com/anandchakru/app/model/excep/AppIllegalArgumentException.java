package com.anandchakru.app.model.excep;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class AppIllegalArgumentException extends AbstractException {
	public AppIllegalArgumentException(ExceptionMeta meta) {
		super(meta);
	}
}
