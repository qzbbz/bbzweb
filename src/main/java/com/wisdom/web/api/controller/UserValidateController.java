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
import com.wisdom.web.utils.SessionConstant;

@Controller
public class UserValidateController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserValidateController.class);

	@Autowired
	private IUserValidateApi userValidateApi;

	@RequestMapping("/checkUserLogin")
	@ResponseBody
	public Map<String, String> checkUserLogin(HttpSession httpSession,
			HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		Map<String, String> retMap = new HashMap<>();
		logger.debug("checkUserLogin");
		if (userId != null && !userId.isEmpty() && userPwd != null
				&& !userPwd.isEmpty()) {
			retMap = userValidateApi.UserLoginValidate(userId, userPwd);
			if (("0").equals(retMap.get("error_code"))) {
				int userTypeId = userValidateApi.getUserTypeByUserId(userId);
				if(userTypeId == 0) {
					retMap.put("error_code", "100");
					retMap.put("error_message", "服务器系统出错了，请您稍后重试！");
				} else if (userTypeId == 2
						&& !userValidateApi.companyHasFinishRegister(userId)) {
					retMap.put("error_code", "4");
				} else {
					httpSession.setAttribute(SessionConstant.SESSION_USER_ID,
							userId);
					httpSession.setAttribute(SessionConstant.SESSION_USER_TYPE,
							userTypeId);
					retMap.put("error_code", "0");
					retMap.put("user_type", String.valueOf(userTypeId));
				}
			}
		} else {
			retMap.put("error_code", "-1");
		}
		logger.debug("finish checkUserLogin");
		return retMap;
	}

	@RequestMapping("/checkUserRegister")
	@ResponseBody
	public Map<String, String> checkUserRegister(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userTypeId = request.getParameter("userTypeId");
		Map<String, String> retMap = new HashMap<>();
		logger.debug("checkUserRegister");
		if (userId != null && !userId.isEmpty() && userPwd != null
				&& !userPwd.isEmpty() && userTypeId != null
				&& !userTypeId.isEmpty()) {
			int utId = Integer.valueOf(userTypeId);
			retMap = userValidateApi
					.UserRegisterValidate(userId, userPwd, utId);
		} else {
			retMap.put("error_code", "-1");
		}
		logger.debug("finish checkUserRegister");
		return retMap;
	}
	
	@RequestMapping("/accounter/admin")
	public String getAccounterAdminHtml(HttpSession httpSession) {
		return "redirect:/views/webviews/accounter/admin.html";
	}
	
	@RequestMapping("/company/expenseAccountUpload")
	public String getCompanyAdminHtml(HttpSession httpSession) {
		return "redirect:/views/webviews/company/expense_account_upload.html";
	}
	
	@RequestMapping("/companyUser/inbox")
	public String getCompanyUserAdminHtml(HttpSession httpSession) {
		return "redirect:/views/webviews/companyUser/inbox.html";
	}
	
	@RequestMapping("/admin/admin")
	public String getAdminAdminHtml(HttpSession httpSession) {
		return "redirect:/views/webviews/admin/admin.html";
	}
}
