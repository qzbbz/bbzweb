package com.wisdom.web.api;

import java.util.Map;


public interface IUserValidateApi {

	public Map<String, String> UserLoginValidate(String userId, String userPwd);
	
	public Map<String, String> UserRegisterValidate(String userId, String userPwd, int userTypeId);
	
	public int getUserTypeByUserId(String userId);
	
	public int getUserAuditStatusByUserId(String userId);
	
	public boolean companyHasFinishRegister(String userId);
		
}
