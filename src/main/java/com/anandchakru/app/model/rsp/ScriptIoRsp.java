package com.anandchakru.app.model.rsp;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class ScriptIoRsp implements Response {
	private String output;
	private String error;
	private List<Response> child;

	public void addChild(Response response) {
		child.add(response);
	}
}
