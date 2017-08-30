package com.ciazhar.authserver.model.dto.request;

public class ChangeEmailForm {

	private String id;
	private String email;
	
	public ChangeEmailForm() {
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
