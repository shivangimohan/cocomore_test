package com.coco.requestVo;

import com.google.gson.annotations.SerializedName;

public class CreatePostVo {

	@SerializedName("title")
	private String title;
	@SerializedName("body")
	private String body;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
