package com.wisdom.web.api;

import java.util.Map;


public interface IUserValidateApi {

	public Map<Integer, String> UserLoginValidate(String userId, String userPwd);
	
	public Map<Integer, String> UserRegisterValidate(String userId, String userPwd, int userTypeId);
	
	public int getUserTypeByUserId(String userId);
	
	public boolean companyHasFinishRegister(String userId);
		
}
