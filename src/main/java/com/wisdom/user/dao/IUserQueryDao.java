package com.wisdom.user.dao;

import com.wisdom.common.model.User;
import com.wisdom.common.model.UserDept;
import com.wisdom.common.model.UserInvitecode;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.common.model.UserPhone;
import com.wisdom.common.model.UserPhoneType;
import com.wisdom.common.model.UserPwd;
import com.wisdom.common.model.UserRole;
import com.wisdom.common.model.UserType;

public interface IUserQueryDao {
	
	public User getUserByUserId(String userId);
	
	public UserPwd getUserPwdByUserId(String userId);
	
	public UserType getUserTypeByUserId(String userId);
	
	public UserInvitecode getUserInvitecodeByInviteCode(String inviteCode);
	
	public UserPhone getUserPhoneByUserId(String userId);
	
	public UserOpenid getUserOpenidByOpenid(String openid);
	
	public UserDept getUserDeptByUserId(String userId);
	
	public UserDept getUserDeptByDeptId(long deptId);
		
	public UserPhoneType getUserPhoneTypeById(long id);
	
	public UserRole getUserRole(String userId);
	
}
