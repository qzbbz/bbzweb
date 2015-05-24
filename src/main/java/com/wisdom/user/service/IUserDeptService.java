package com.wisdom.user.service;

import java.util.List;

import com.wisdom.common.model.UserDept;

public interface IUserDeptService {

	public long getDeptIdByUserId(String userId);
	public long getUserNumByDeptId(long deptId);
	public List<UserDept> getUserDeptListByDeptId(long deptId);
	public boolean delUserByDeptId(long deptId);
}
