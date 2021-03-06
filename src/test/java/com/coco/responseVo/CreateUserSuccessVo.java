package com.coco.responseVo;

import com.google.gson.annotations.SerializedName;

public class CreateUserSuccessVo {

	@SerializedName("code")
	private Integer code;
	@SerializedName("meta")
	private Object meta;
	@SerializedName("data")
	private UserData data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getMeta() {
		return meta;
	}

	public void setMeta(Object meta) {
		this.meta = meta;
	}

	public UserData getData() {
		return data;
	}

	public void setData(UserData data) {
		this.data = data;
	}

}