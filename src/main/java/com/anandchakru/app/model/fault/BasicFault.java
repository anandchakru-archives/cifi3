package com.anandchakru.app.model.fault;

@SuppressWarnings("serial")
public class BasicFault implements Fault {
	private String description;

	public BasicFault() {
		super();
	}
	public BasicFault(String description) {
		super();
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}