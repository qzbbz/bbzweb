package com.wisdom.user.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wisdom.common.model.UserInvitecode;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.user.dao.IUserOperationDao;
import com.wisdom.user.dao.IUserQueryDao;
import com.wisdom.user.service.IUserWeixinService;

public class UserWeixinService implements IUserWeixinService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserWeixinService.class);

	@Autowired
	private IUserQueryDao userQueryDao;
	
	@Autowired
	private IUserOperationDao userOpeDao;
	
	@Override
	public boolean userHasBindCompany(String openid) {
		UserOpenid userOpenid = userQueryDao.getUserOpenidByOpenid(openid);
		return userOpenid != null ? true : false;
	}

	@Override
	public boolean userBindCompany(String inviteCode, String openid) {
		boolean isBindSuccess = false;
		UserInvitecode userInviteCode = userQueryDao.getUserInvitecodeByInviteCode(inviteCode);
		if(userInviteCode != null) {
			String userId = userInviteCode.getUserId();
			UserOpenid userOpenid = new UserOpenid();
			userOpenid.setOpenid(openid);
			userOpenid.setUserId(userId);
			userOpenid.setCreateTime(new Timestamp(System.currentTimeMillis()));
			userOpeDao.addOpenid(userOpenid);
			isBindSuccess = true;
			logger.debug("User succeeds in binding the company.");
		}
		return isBindSuccess;
	}

}