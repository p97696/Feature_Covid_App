package com.apple.wipro.corona.entity;

import org.springframework.stereotype.Component;

@Component
public class CovidBean {
	private String error;
	private String statusCode;
	private String message;
	private CovidData data;
	
	
	public CovidBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CovidBean(String error, String statusCode, String message, CovidData data) {
		super();
		this.error = error;
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CovidData getData() {
		return data;
	}
	public void setData(CovidData data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "CovidBean [error=" + error + ", statusCode=" + statusCode + ", message=" + message + ", data=" + data
				+ "]";
	}
	
	
	
	}


