package com.wisdom.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.User;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.common.model.UserPwd;
import com.wisdom.common.model.UserType;
import com.wisdom.user.dao.IUserOperationDao;
import com.wisdom.user.dao.IUserQueryDao;
import com.wisdom.user.service.IUserService;

@Service("userInfoService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserQueryDao userQueryDao;
	
	@Autowired
	private IUserOperationDao userOperationDao;

	@Override
	public String getUserPwdByUserId(String userId) {
		UserPwd userPwd = userQueryDao.getUserPwdByUserId(userId);
		return userPwd != null ? userPwd.getPwd() : "";
	}

	@Override
	public int getUserTypeIdByUserId(String userId) {
		UserType userType = userQueryDao.getUserTypeByUserId(userId);
		return userType != null ? userType.getId() : 0;
	}

	@Override
	public boolean userIdExist(String userId) {
		User user = userQueryDao.getUserByUserId(userId);
		return user != null ? true : false;
	}

	@Override
	public boolean addUser(User user) {
		return userOperationDao.addUser(user);
	}

	@Override
	public long getCompanyIdByUserId(String userId) {
		User user = userQueryDao.getUserByUserId(userId);
		return user != null ? user.getCompanyId() : 0;
	}

	@Override
	public String getUserIdByOpenId(String openId) {
		UserOpenid userOpenid = userQueryDao.getUserOpenidByOpenid(openId);
		return userOpenid != null ? userOpenid.getUserId() : "";
	}

}
