package com.wisdom.user.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wisdom.common.model.User;
import com.wisdom.common.model.UserPwd;
import com.wisdom.common.model.UserType;
import com.wisdom.user.dao.IUserOperationDao;
import com.wisdom.user.dao.IUserQueryDao;
import com.wisdom.user.service.IUserLoginAndRegisterService;

public class UserLoginAndRegisterServiceImpl implements
		IUserLoginAndRegisterService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserLoginAndRegisterServiceImpl.class);

	@Autowired
	private IUserQueryDao userQueryDao;

	@Autowired
	private IUserOperationDao userOpeDao;

	@Override
	public boolean userLoginCheck(String userId, String userPwd) {
		boolean checkRet = false;
		UserPwd uPwd = userQueryDao.getUserPwdByUserId(userId);
		if (uPwd != null && !uPwd.getPwd().isEmpty()) {
			String encryptPwd = getPasswordByMD5Encrypt(uPwd.getPwd());
			if (encryptPwd != null && encryptPwd.equals(uPwd.getPwd()))
				checkRet = true;
		}
		return checkRet;
	}

	@Override
	public boolean userRegisterCheck(String userId, String userPwd,
			String userType) {
		boolean checkStatus = false;
		User user = userQueryDao.getUserByUserId(userId);
		if (user == null) {
			String encPwd = getPasswordByMD5Encrypt(userPwd);
			if (encPwd != null && !encPwd.isEmpty()) {
				UserType uType = userQueryDao.getUserTypeByUserId(userId);
				if (uType != null) {
					user = new User();
					user.setUserId(userId);
					user.setTypeId(uType.getId());
					user.setCreateTime(new Timestamp(System.currentTimeMillis()));
					userOpeDao.addUser(user);
					checkStatus = true;
				}
			}
		}
		return checkStatus;
	}

	private String getPasswordByMD5Encrypt(String pwd) {
		byte[] buf = pwd.getBytes();
		MessageDigest md5;
		StringBuilder sb = new StringBuilder();
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(buf);
			byte[] tmp = md5.digest();
			for (byte b : tmp) {
				sb.append(Integer.toHexString(b & 0xff));
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.toString());
		}
		return sb.toString();
	}

}
