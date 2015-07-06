package com.wisdom.user.service;

public interface IUserWeixinService {
	
	public boolean userHasBindCompany(String openid);
	
	public String getUserIdByUserInviteCode(String inviteCode);

	public boolean addOpenId(String userId, String openId);
	
	public boolean deleteUserOpenidByOpenid(String openId);
	
}