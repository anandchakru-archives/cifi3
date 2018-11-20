package com.anandchakru.app.model.rsp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class FirstRunHomeRsp implements Response {
	private String appname;
}
