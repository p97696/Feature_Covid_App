package com.apple.wipro.corona.entity;

import java.io.Serializable;

public class CovidTotalBean implements Serializable {
	private String error;
	private String statusCode;
	private String message;
	private CovidTotal data;
	public CovidTotalBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CovidTotalBean(String error, String statusCode, String message, CovidTotal data) {
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
	public CovidTotal getData() {
		return data;
	}
	public void setData(CovidTotal data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "CovidTotalBean [error=" + error + ", statusCode=" + statusCode + ", message=" + message + ", data="
				+ data + "]";
	}
	
	

}
