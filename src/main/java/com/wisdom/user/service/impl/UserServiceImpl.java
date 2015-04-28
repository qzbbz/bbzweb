package com.wisdom.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.User;
import com.wisdom.user.dao.IUserQueryDao;
import com.wisdom.user.service.IUserService;

@Service("userInfoService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserQueryDao userQueryDao;

	@Override
	public String getUserPwdByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUserTypeIdByUserId(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean userIdExist(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getCompanyIdByUserId(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
