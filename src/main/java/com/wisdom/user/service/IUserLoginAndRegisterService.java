package com.wisdom.user.service;

public interface IUserLoginAndRegisterService {

	public boolean userLoginCheck(String userId, String userPwd);
	
	public boolean userRegisterCheck(String userId, String userPwd, long userType);
	
}