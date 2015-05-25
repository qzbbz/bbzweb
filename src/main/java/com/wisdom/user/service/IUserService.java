package com.wisdom.user.service;

import java.util.List;

import com.wisdom.common.model.User;
import com.wisdom.common.model.UserPhone;

public interface IUserService {
	
	public String getUserPwdByUserId(String userId);
	
	public boolean updateUserInfo(String userId,String userName,int typeId,String userCode,String userLevel,String msgMail);
	
	public String getUserMsgEmailByUserId(String userId);
	
	public boolean updateUserMsgEmailByUserId(String msgEamil, String userId);
	
	public int getUserTypeIdByUserId(String userId);
	
	public boolean updateUserRegStatusByUserId(int status, String userId);
	
	public boolean userIdExist(String userId);
	
	public boolean addUser(User user);
	
	public boolean addUserPhone(String userId, String userPhone, int phoneTypeId);
	
	public long getCompanyIdByUserId(String userId);
	
	public String getUserIdByOpenId(String openId);
	
	public String getOpenIdByUserId(String userId);
	
	public String getUserNameByUserId(String userId);
	
	public boolean setUserNameByUserId(String userName, String userId);
	
	public boolean setUserPwdByUserId(String userPwd, String userId);
	
	public boolean checkUserAuth(String userId,String appovalUser);
	
	public List<String> getApprovalUserList(String userId);
	
	public boolean ifNeedSuperApproval(String userId,String approvalId,double amount);
	
	public boolean deleteUser(String userId);
	
}
