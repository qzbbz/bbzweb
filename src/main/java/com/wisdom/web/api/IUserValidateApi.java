package com.wisdom.web.api;

import java.util.Map;


public interface IUserValidateApi {

	public Map<String, String> UserLoginValidate(String userId, String userPwd);
	
	public Map<String, String> UserRegisterValidate(String userId, String userPwd, int userTypeId);
	
	public Map<String, String> UserForgetPwd(String userId);
	
	public int getUserTypeByUserId(String userId);
	
	public int getUserAuditStatusByUserId(String userId);
	
	public boolean companyHasFinishRegister(String userId);
		
}
