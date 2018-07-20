package com.zuidaima.entity;

public class Result {

	String code;
	
	String message;
	
	String result;

	public Result(String code, String message, String result) {
		super();
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
