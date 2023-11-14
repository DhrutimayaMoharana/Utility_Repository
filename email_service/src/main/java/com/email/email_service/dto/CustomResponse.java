package com.email.email_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {

	private Integer responseCode;

	private Object data;

	private String responseDescription;

	public CustomResponse() {
		super();
	}

	public CustomResponse(Integer responseCode, Object data, String responseDescription) {
		super();
		this.responseCode = responseCode;
		this.data = data;
		this.responseDescription = responseDescription;
	}

	public CustomResponse(Integer responseCode, String responseDescription) {
		super();
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

}
