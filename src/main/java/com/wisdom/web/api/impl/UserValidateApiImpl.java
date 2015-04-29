package com.wisdom.web.api.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.User;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.IUserValidateApi;
import com.wisdom.web.utils.ErrorCode;

@Service("userValidateApiService")
public class UserValidateApiImpl implements IUserValidateApi {

	private static final Logger logger = LoggerFactory
			.getLogger(UserValidateApiImpl.class);
	
	@Autowired
	private IUserService userService;

	@Override
	public Map<Integer, String> UserLoginValidate(String userId, String userPwd) {
		Map<Integer, String> retMap = new HashMap<>();
		if(userService.userIdExist(userId)) {
			String enPwd = userService.getUserPwdByUserId(userId);
			if(enPwd.equals(getPasswordByMD5Encrypt(userPwd))) {
				retMap.put(ErrorCode.NO_ERROR_CODE, ErrorCode.NO_ERROR_MESSAGE);
			} else {
				retMap.put(ErrorCode.USER_PWD_ERROR_CODE, ErrorCode.USER_PWD_ERROR_MESSAGE);
			}
		} else {
			retMap.put(ErrorCode.USER_ID_ERROR_CODE, ErrorCode.USER_ID_ERROR_MESSAGE);
		}
		return retMap;
	}

	@Override
	public Map<Integer, String> UserRegisterValidate(String userId,
			String userPwd, int userTypeId) {
		Map<Integer, String> retMap = new HashMap<>();
		if(!userService.userIdExist(userId)) {
			User user = new User();
			user.setUserId(userId);
			user.setTypeId(userTypeId);
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			userService.addUser(user);
			retMap.put(ErrorCode.NO_ERROR_CODE, ErrorCode.NO_ERROR_MESSAGE);
		} else {
			retMap.put(ErrorCode.USER_ID_EXIST_ERROR_CODE, ErrorCode.USER_ID_EXIST_ERROR_MESSAGE);
		}
		return retMap;
	}
	
	@Override
	public int getUserTypeByUserId(String userId) {
		return userService.getUserTypeIdByUserId(userId);
	}
	
	@Override
	public boolean companyHasFinishRegister(String userId) {
		if(userService.getCompanyIdByUserId(userId) < 0)
			return false;
		else
			return true;
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
