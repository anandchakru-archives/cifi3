package com.anandchakru.app.model.rsp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class ShutdownRsp implements Response {
	private String output;
	private String error;
}
