package com.ciazhar.authserver.model.dto.request;

import java.util.Date;

public class ChangeBirthDateForm {

	private String id;
	private Date birthDate;

	public ChangeBirthDateForm() {
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
