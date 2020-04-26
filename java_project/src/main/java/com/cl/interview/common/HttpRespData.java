package com.cl.interview.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpRespData<T> {
	private Integer status;
	private String msg;
	private T data;
	private Boolean success;

	public HttpRespData(String msg, Boolean success) {
		this.msg = msg;
		this.success = success;
	}

	public HttpRespData(Integer status, String msg, Boolean success) {
		this.status = status;
		this.msg = msg;
		this.success = success;
	}

	public HttpRespData(Integer status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public HttpRespData() {
		super();
	}

}
