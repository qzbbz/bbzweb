package com.wisdom.web.utils;

public class ErrorCode {

	public static final int NO_ERROR_CODE = 0;
	public static final int USER_ID_ERROR_CODE = 100000;
	public static final int USER_PWD_ERROR_CODE = 100100;
	public static final int USER_ID_EXIST_ERROR_CODE = 100200;
	public static final int USER_ID_OR_PWD_ERROR_CODE = 100300;
	public static final int USER_ID_OR_PWD_EMPTY_ERROR_CODE = 100400;
	
	public static final int COMPANY_REGISTER_ERROR_CODE = 200000;
	public static final int COMPANY_REGISTER_INFO_EMPTY_ERROR_CODE = 200100;
	
	public static final int ACCOUNTER_UPDATE_INFO_ERROR_CODE = 300000;
	
	public static final int COMMUNITY_PUBLISH_ARTICLE_ERROR_CODE = 400000;
	
	public static final int COMMENT_PUBLISH_ERROR_CODE = 500000;

	public static final String NO_ERROR_MESSAGE = "";
	public static final String USER_ID_ERROR_MESSAGE = "User id is wrong.";
	public static final String USER_PWD_ERROR_MESSAGE = "User pwd is wrong.";
	public static final String USER_ID_EXIST_ERROR_MESSAGE = "User id has existed.";
	public static final String USER_ID_OR_PWD_ERROR_MESSAGE = "User id or password is wrong.";
	public static final String USER_ID_OR_PWD_EMPTY_ERROR_MESSAGE = "User id or pwd is empty.";
	
	public static final String COMPANY_REGISTER_ERROR_MESSAGE = "Failed in adding a company.";
	public static final String COMPANY_REGISTER_INFO_EMPTY_ERROR_MESSAGE = "Company register infos are empty.";

	public static final String ACCOUNTER_UPDATE_INFO_ERROR_MESSAGE = "Failed in updating accounter info.";
	
	public static final String COMMUNITY_PUBLISH_ARTICLE_ERROR_MESSAGE = "Failed in publishing article.";
	
	public static final String COMMENT_PUBLISH_ERROR_MESSAGE = "Failed in publishing comment.";
}