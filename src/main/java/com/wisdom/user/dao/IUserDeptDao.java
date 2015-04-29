package com.wisdom.user.dao;

import com.wisdom.common.model.UserDept;

public interface IUserDeptDao {

	public UserDept getUserDeptByUserId(String userId);
	
}