package com.wisdom.user.service.impl;

import com.wisdom.common.model.UserInviteCode;
import com.wisdom.user.dao.IUserInviteCodeDao;
import com.wisdom.user.dao.IUserOpenIdDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.UserOpenid;
import com.wisdom.user.dao.IUserOperationDao;
import com.wisdom.user.dao.IUserQueryDao;
import com.wisdom.user.service.IUserWeixinService;

@Service("userWeixinService")
public class UserWeixinService implements IUserWeixinService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserWeixinService.class);

	@Autowired
	private IUserQueryDao userQueryDao;

	@Autowired
	private IUserOperationDao userOperationDao;

	@Autowired
	private IUserOpenIdDao userOpenIdDao;

	@Autowired
	private IUserInviteCodeDao userInviteCodeDao;

	@Override
	public boolean userHasBindCompany(String openid) {
		UserOpenid userOpenid = userQueryDao.getUserOpenidByOpenid(openid);
		logger.debug("userOpenid is {}", userOpenid);
		return userOpenid != null ? true : false;
	}

	@Override
	public String getUserIdByUserInviteCode(String inviteCode) {
		UserInviteCode userInviteCode = userInviteCodeDao
				.getUserInviteCodeByInviteCode(inviteCode);
		return userInviteCode != null ? userInviteCode.getUserId() : null;
	}

	@Override
	public boolean addOpenId(String userId, String openId) {
		UserOpenid userOpenid = userOpenIdDao.getUserOpenidByUserId(userId);
		if (userOpenid != null)
			return true;
		return userOpenIdDao.addUserOpenid(userId, openId);
	}

}