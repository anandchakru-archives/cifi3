package com.anandchakru.app.model.rsp;

import java.io.Serializable;
import com.anandchakru.app.model.excep.ExceptionMeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class AppResponse implements Serializable {
	private Response response;
	private ExceptionMeta meta;
}