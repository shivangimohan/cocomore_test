package com.coco.responseVo;

import com.google.gson.annotations.SerializedName;

public class DeleteSuccessVo {

	@SerializedName("code")
	private Integer code;
	@SerializedName("meta")
	private Object meta;
	@SerializedName("data")
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
