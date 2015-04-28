package com.wisdom.web.service;

import java.util.Map;


public interface IUserValidateService {

	public Map<Integer, String> UserLoginValidate(String userId, String userPwd);
	
	public Map<Integer, String> UserRegisterValidate(String userId, String userPwd, long userTypeId);
		
}
