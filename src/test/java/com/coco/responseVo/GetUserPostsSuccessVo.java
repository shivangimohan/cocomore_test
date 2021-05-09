package com.coco.responseVo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetUserPostsSuccessVo {

	@SerializedName("code")
	private Integer code;
	@SerializedName("meta")
	private Meta meta;
	@SerializedName("data")
	private List<Datum> data = null;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Datum> getData() {
		return data;
	}

	public void setData(List<Datum> data) {
		this.data = data;
	}

}