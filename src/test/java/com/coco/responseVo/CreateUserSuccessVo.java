package com.coco.responseVo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateUserSuccessVo {

	@SerializedName("code")
	@Expose
	private Integer code;
	@SerializedName("meta")
	@Expose
	private Object meta;
	@SerializedName("data")
	@Expose
	private Data data;

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

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}