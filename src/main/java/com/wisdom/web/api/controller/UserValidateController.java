package com.wisdom.web.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.web.api.IUserValidateApi;
import com.wisdom.web.utils.ErrorCode;
import com.wisdom.web.utils.SessionConstant;

@Controller
public class UserValidateController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserValidateController.class);

	@Autowired
	private IUserValidateApi userValidateApi;

	@RequestMapping("/checkUserLogin")
	@ResponseBody
	public Map<Integer, String> checkUserLogin(HttpSession httpSession,
			HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		Map<Integer, String> retMap = new HashMap<>();
		logger.debug("checkUserLogin");
		if (userId != null && !userId.isEmpty() && userPwd != null
				&& !userPwd.isEmpty()) {
			retMap = userValidateApi.UserLoginValidate(userId, userPwd);
			if (retMap.containsKey(ErrorCode.NO_ERROR_CODE)) {
				httpSession.setAttribute(SessionConstant.SESSION_USER_ID,
						userId);
				int userTypeId = userValidateApi.getUserTypeByUserId(userId);
				httpSession.setAttribute(SessionConstant.SESSION_USER_TYPE,
						userTypeId);
				if (userTypeId == 2
						&& !userValidateApi.companyHasFinishRegister(userId)) {
					httpSession
							.setAttribute(
									SessionConstant.SESSION_COMPANY_NOT_FINISH_REGISTER,
									userId);
				}
			}
		} else {
			retMap.put(ErrorCode.USER_ID_OR_PWD_EMPTY_ERROR_CODE,
					ErrorCode.USER_ID_OR_PWD_EMPTY_ERROR_MESSAGE);
		}
		logger.debug("finish checkUserLogin");
		return retMap;
	}

	@RequestMapping("/checkUserRegister")
	@ResponseBody
	public Map<Integer, String> checkUserRegister(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userTypeId = request.getParameter("userTypeId");
		Map<Integer, String> retMap = new HashMap<>();
		logger.debug("checkUserRegister");
		if (userId != null && !userId.isEmpty() && userPwd != null
				&& !userPwd.isEmpty() && userTypeId != null
				&& !userTypeId.isEmpty()) {
			int utId = Integer.valueOf(userTypeId);
			retMap = userValidateApi
					.UserRegisterValidate(userId, userPwd, utId);
		} else {
			retMap.put(ErrorCode.USER_ID_OR_PWD_EMPTY_ERROR_CODE,
					ErrorCode.USER_ID_OR_PWD_EMPTY_ERROR_MESSAGE);
		}
		logger.debug("finish checkUserRegister");
		return retMap;
	}
}
