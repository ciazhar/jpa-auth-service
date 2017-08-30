package com.ciazhar.authserver.model.dto.request;

public class ChangeUsernameForm {

	private String id;
	private String username;
	
	public ChangeUsernameForm() {
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
