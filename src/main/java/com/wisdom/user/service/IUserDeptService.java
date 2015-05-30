package com.wisdom.user.service;

import java.util.List;

import com.wisdom.common.model.UserDept;
import com.wisdom.user.domain.UserInfo;

public interface IUserDeptService {

	public long getDeptIdByUserId(String userId);
	public long getUserNumByDeptId(long deptId);
	public List<UserDept> getUserDeptListByDeptId(long deptId);
	public List<UserDept> getUserDeptListByDeptIdAndUserId(long deptId, String userId);
	public boolean delUserByDeptId(long deptId);
	public boolean updateUserDeptStatus(String userId,int status);
	
	
	
	public boolean addDeptUser(String userId,String userName,int iCharger,long iDeptId,String userCode,
			String userLevel,long iCompanyId,String msgMail);
	
	public boolean delDeptUser(String userId,long deptId);
	
	/*根据条件查询用户：deptId暂时没用到*/
	public List<UserInfo> queryUser(String userId,long iDeptId,String userName,String userCode);
}
