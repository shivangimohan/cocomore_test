package com.coco.requestVo;

import com.google.gson.annotations.SerializedName;

public class CreateCommentVo {

	@SerializedName("name")
	private String name;
	@SerializedName("email")
	private String email;
	@SerializedName("body")
	private String body;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
