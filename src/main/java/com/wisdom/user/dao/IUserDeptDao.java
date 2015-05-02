package com.wisdom.user.dao;

import java.util.List;

import com.wisdom.common.model.UserDept;

public interface IUserDeptDao {

	public UserDept getUserDeptByUserId(String userId);
	
	public List<UserDept> getUserDeptListByDeptId(long deptId);
	
}