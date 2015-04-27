package com.wisdom.user.service;

public interface IUserWeixinService {
	
	public boolean userHasBindCompany(String openid);
	
	public boolean userBindCompany(String openid, String inviteCode);
	
}
