package com.wisdom.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.user.service.IUserLoginAndRegisterService;
import com.wisdom.web.errorcode.ErrorCode;
import com.wisdom.web.service.IUserValidateService;

@Service("userValidateService")
public class UserValidateServiceImpl implements IUserValidateService {

	@Autowired
	private IUserLoginAndRegisterService userLoginAndRegisterService;

	@Override
	public Map<Integer, String> UserLoginValidate(String userId, String userPwd) {
		Map<Integer, String> retMap = new HashMap<>();
		if (userLoginAndRegisterService.userLoginCheck(userId, userPwd)) {
			retMap.put(ErrorCode.NO_ERROR_CODE, ErrorCode.NO_ERROR_MESSAGE);
		} else {
			retMap.put(ErrorCode.USER_ID_OR_PWD_ERROR_CODE, ErrorCode.USER_ID_OR_PWD_ERROR_MESSAGE);
		}
		return retMap;
	}

	@Override
	public Map<Integer, String> UserRegisterValidate(String userId,
			String userPwd, long userTypeId) {
		Map<Integer, String> retMap = new HashMap<>();
		if(userLoginAndRegisterService.userRegisterCheck(userId, userPwd, userTypeId)) {
			retMap.put(ErrorCode.NO_ERROR_CODE, ErrorCode.NO_ERROR_MESSAGE);
		} else {
			retMap.put(ErrorCode.USER_ID_EXIST_ERROR_CODE, ErrorCode.USER_ID_EXIST_ERROR_MESSAGE);
		}
		return retMap;
	}

}
