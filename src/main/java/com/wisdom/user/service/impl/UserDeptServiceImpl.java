package com.wisdom.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.user.dao.IUserDeptDao;
import com.wisdom.user.service.IUserDeptService;

@Service("userDeptService")
public class UserDeptServiceImpl implements IUserDeptService {

	@Autowired
	private IUserDeptDao userDeptDao;
	
	@Override
	public long getDeptIdByUserId(String userId) {
		return userDeptDao.getUserDeptByUserId(userId).getDeptId();
	}
}