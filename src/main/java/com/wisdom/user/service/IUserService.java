package com.wisdom.user.service;

import java.util.List;

import com.wisdom.common.model.User;

public interface IUserService {
	
	public String getUserPwdByUserId(String userId);
	
	public int getUserTypeIdByUserId(String userId);
	
	public boolean userIdExist(String userId);
	
	public boolean addUser(User user);
	
	public long getCompanyIdByUserId(String userId);
	
	public String getUserIdByOpenId(String openId);
	
	public String getOpenIdByUserId(String userId);
	
	public String getUserNameByUserId(String userId);
	
	public boolean setUserNameByUserId(String userName, String userId);
	
	public boolean checkUserAuth(String userId,String appovalUser);
	
	public List<String> getApprovalUserList(String userId);
	
	public boolean ifNeedSuperApproval(String userId,String approvalId,double amount);
	
}
