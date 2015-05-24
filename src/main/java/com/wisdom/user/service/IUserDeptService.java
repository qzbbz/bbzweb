package com.wisdom.user.service;

import java.util.List;

import com.wisdom.common.model.UserDept;

public interface IUserDeptService {

	public long getDeptIdByUserId(String userId);
	public long getUserNumByDeptId(long deptId);
	public List<UserDept> getUserDeptListByDeptId(long deptId);
	public boolean delUserByDeptId(long deptId);
	
	public boolean addDeptUser(String userId,String userName,int iCharger,long iDeptId,String userCode,
			String userLevel,long iCompanyId,String msgMail);
}
