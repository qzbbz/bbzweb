package com.wisdom.user.service;

import com.wisdom.common.model.User;

public interface IUserService {
	
	public String getUserPwdByUserId(String userId);
	
	public int getUserTypeIdByUserId(String userId);
	
	public boolean userIdExist(String userId);
	
	public boolean addUser(User user);
	
	public long getCompanyIdByUserId(String userId);
	
	public String getUserIdByOpenId(String openId);
	
}
