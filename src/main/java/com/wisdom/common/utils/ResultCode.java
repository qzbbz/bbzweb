package com.wisdom.common.utils;

public enum ResultCode {
	/**
	 * 调用成功
	 */
	success("0"),
	/***
	 * 系统内部错误
	 */
	systemError("-1"),
	/**
	 * 参数错误
	 */
	paramError("1"),
	/**
	 * 业务校验错误
	 */
	serviceError("1"),
	/**
	 * not login
	 */
	notLogin("2"),
	/**
	 * 参数错误
	 */
	userEmailExist("10");

	public String code;
	public String message; //错误描述
	ResultCode(String code) {
		this.code = code;
	}

	ResultCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String toString() {
		return code;
	}
}
