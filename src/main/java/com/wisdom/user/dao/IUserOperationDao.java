package com.wisdom.user.dao;

import com.wisdom.common.model.User;
import com.wisdom.common.model.UserDept;
import com.wisdom.common.model.UserInviteCode;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.common.model.UserPhone;
import com.wisdom.common.model.UserPhoneType;
import com.wisdom.common.model.UserRole;

public interface IUserOperationDao {

	public boolean addUser(User user);
	
	public boolean deleteUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean updateUserMsgEmail(String email, String userId);
	
	public boolean updateUserNameByUserId(String userName, String userId);
	
	public boolean addInviteCode(UserInviteCode userInvitecode);
	
	public boolean deleteInviteCode(UserInviteCode userInvitecode);
	
	public boolean updateInviteCode(UserInviteCode userInviteCode);
	
	public boolean addOpenid(UserOpenid userOpenid);
	
	public boolean deleteOpenid(UserOpenid userOpenid);
	
	public boolean updateOpenid(UserOpenid userOpenid);
	
	public boolean addUserPhone(UserPhone userPhone);
	
	public boolean deleteUserPhone(UserPhone userPhone);
	
	public boolean updateUserPhone(UserPhone userPhone);
	
	public boolean addUserDept(UserDept userDept);
	
	public boolean deleteUserDept(UserDept userDept);
	
	public boolean updateUserDept(UserDept userDept);
	
	public boolean addUserPhoneType(UserPhoneType userPhoneType);
	
	public boolean deleteUserPhoneType(UserPhoneType userPhoneType);
	
	public boolean updateUserPhoneType(UserPhoneType userPhoneType);
	
	public boolean addUserRole(UserRole userRole);
	
	public boolean deleteUserRole(UserRole userRole);
	
	public boolean updateUserRole(UserRole userRole);
	
}