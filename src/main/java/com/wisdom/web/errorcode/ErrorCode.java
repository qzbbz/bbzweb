package com.wisdom.web.errorcode;

public class ErrorCode {

	public static final int NO_ERROR_CODE = 0;
	public static final int USER_ID_ERROR_CODE = 100000;
	public static final int USER_PWD_ERROR_CODE = 100100;
	public static final int USER_ID_EXIST_ERROR_CODE = 100200;
	public static final int USER_ID_OR_PWD_ERROR_CODE = 100300;
	
	public static final int COMPANY_REGISTER_ERROR_CODE = 20000;

	public static final String NO_ERROR_MESSAGE = "";
	public static final String USER_ID_ERROR_MESSAGE = "User id is wrong.";
	public static final String USER_PWD_ERROR_MESSAGE = "User pwd is wrong.";
	public static final String USER_ID_EXIST_ERROR_MESSAGE = "User id has existed.";
	public static final String USER_ID_OR_PWD_ERROR_MESSAGE = "User id or password is wrong.";
	
	public static final String COMPANY_REGISTER_ERROR_MESSAGE = "Failed in adding a company.";
}