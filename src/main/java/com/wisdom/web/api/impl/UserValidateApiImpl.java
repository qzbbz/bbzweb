package com.wisdom.web.api.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.accounter.service.IAccounterService;
import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.User;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.IUserValidateApi;
import com.wisdom.web.utils.UserPwdMD5Encrypt;

@Service("userValidateApiService")
public class UserValidateApiImpl implements IUserValidateApi {

	private static final Logger logger = LoggerFactory
			.getLogger(UserValidateApiImpl.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAccounterService accounterService;

	@Override
	public Map<String, String> UserLoginValidate(String userId, String userPwd) {
		Map<String, String> retMap = new HashMap<>();
		if (userService.userIdExist(userId)) {
			String enPwd = userService.getUserPwdByUserId(userId);
			if (enPwd.equals(UserPwdMD5Encrypt.getPasswordByMD5Encrypt(userPwd))) {
				retMap.put("error_code", "0");
			} else {
				retMap.put("error_code", "1");
				retMap.put("error_message", "您输入的密码不正确，请检查！");
			}
		} else {
			retMap.put("error_code", "2");
			retMap.put("error_message", "您输入的电子邮件不正确，请检查！");
		}
		logger.debug("retMap : {}", retMap);
		return retMap;
	}

	@Override
	public Map<String, String> UserRegisterValidate(String userId,
			String userPwd, int userTypeId) {
		Map<String, String> retMap = new HashMap<>();
		if (!userService.userIdExist(userId)) {
			User user = new User();
			user.setUserId(userId);
			user.setTypeId(userTypeId);
			user.setCompanyId((long) -1);
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			if (userService.addUser(user)
					&& userService.setUserPwdByUserId(
							UserPwdMD5Encrypt.getPasswordByMD5Encrypt(userPwd),
							userId)) {				
				retMap.put("error_code", "0");
			} else {
				retMap.put("error_code", "100");
				retMap.put("error_message", "服务器系统出错了，请您稍后重试！");
			}
		} else {
			retMap.put("error_code", "1");
			retMap.put("error_message", "当前电子邮件地址已经注册过了！");
		}
		return retMap;
	}

	@Override
	public int getUserTypeByUserId(String userId) {
		return userService.getUserTypeIdByUserId(userId);
	}

	@Override
	public boolean companyHasFinishRegister(String userId) {
		if (userService.getCompanyIdByUserId(userId) < 0)
			return false;
		else
			return true;
	}
}
