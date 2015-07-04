package com.wisdom.user.dao;

import java.util.List;

import com.wisdom.common.model.User;
import com.wisdom.common.model.UserDept;
import com.wisdom.common.model.UserInviteCode;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.common.model.UserPhone;
import com.wisdom.common.model.UserPhoneType;
import com.wisdom.common.model.UserPwd;
import com.wisdom.common.model.UserRole;
import com.wisdom.common.model.UserType;

public interface IUserQueryDao {
	
	public User getUserByUserId(String userId);
	
	public List<User> getAccounterUserList();
	
	public List<User> getUserListByCompanyId(long companyId);
	
	public UserPwd getUserPwdByUserId(String userId);
	
	public UserType getUserTypeById(long id);
	
	public UserInviteCode getUserInvitecodeByInviteCode(String inviteCode);
	
	public UserInviteCode getUserInvitecodeByUserId(String userId);
	
	public UserPhone getUserPhoneByUserId(String userId);
	
	public UserOpenid getUserOpenidByOpenid(String openid);
	
	public UserOpenid getUserOpenidByUserId(String userId);
	
	public UserDept getUserDeptByUserId(String userId);
	
	public UserDept getUserDeptByDeptId(long deptId);
		
	public UserPhoneType getUserPhoneTypeById(long id);
	
	public UserRole getUserRole(String userId);
	
}
