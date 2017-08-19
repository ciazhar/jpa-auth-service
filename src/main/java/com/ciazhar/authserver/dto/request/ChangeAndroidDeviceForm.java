package com.ciazhar.authserver.dto.request;

public class ChangeAndroidDeviceForm {

	private String id;
	private String androidDeviceId;
	
	public ChangeAndroidDeviceForm() {
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAndroidDeviceId() {
		return androidDeviceId;
	}

	public void setAndroidDeviceId(String androidDeviceId) {
		this.androidDeviceId = androidDeviceId;
	}
	
}
