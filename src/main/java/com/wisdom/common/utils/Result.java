package com.wisdom.common.utils;

import java.util.HashMap;
import java.util.Map;

public class Result {
	private String code = "0";
	/**
	 * 是否成功
	 */
//	private boolean success;

	private String msg;

	private Map<String,Object> container = new HashMap<String,Object>();
//	public boolean isSuccess() {
//		return success;
//	}

//	public void setSuccess(boolean success) {
//		this.success = success;
//	}
	/**
	 * 设置返回的code
	 * */
	public void setResultCode(String code){
		this.code = code;
	}

	public String getResultCode(){
		return this.code;
	}

	/**
	 * 向Result中添加自定义的结果
	 * */
	public void addResult(String key,Object value){
		container.put(key, value);
	}

	public Map<String,Object> getResultMap(){
		container.put("code", this.code);
		return this.container;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
