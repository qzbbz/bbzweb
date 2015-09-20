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

import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.IUserValidateApi;
import com.wisdom.web.utils.SessionConstant;
import com.wisdom.web.utils.UserPwdMD5Encrypt;

@Controller
public class UserValidateController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserValidateController.class);

	@Autowired
	private IUserValidateApi userValidateApi;
	
	@Autowired
	private IUserService userService;

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
				} else if((userTypeId == 1 || userTypeId == 2) && userValidateApi.getUserAuditStatusByUserId(userId) == 0) {
					retMap.put("error_code", "100");
				} else if((userTypeId == 1 || userTypeId == 2) && userValidateApi.getUserAuditStatusByUserId(userId) == 2) {
					retMap.put("error_code", "102");
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
	
	@RequestMapping("/modifyPassword")
	@ResponseBody
	public Map<String, String> modifyPassword(HttpServletRequest request) {
		Map<String,String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			retMap.put("error_code", "1");
			retMap.put("error_msg", "会话已过期。请退出系统，重新登陆后再次尝试!");
			return retMap;
		}
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		if(oldPwd == null || oldPwd.isEmpty()) {
			retMap.put("error_code", "2");
			retMap.put("error_msg", "服务器无法获取您输入的旧密码或旧密码输入为空!");
			return retMap;
		}
		if(newPwd == null || newPwd.isEmpty()) {
			retMap.put("error_code", "3");
			retMap.put("error_msg", "服务器无法获取您输入的新密码或新密码输入为空!");
			return retMap;
		}
		if (userService.userIdExist(userId)) {
			String enPwd = userService.getUserPwdByUserId(userId);
			if (enPwd.equals(UserPwdMD5Encrypt.getPasswordByMD5Encrypt(oldPwd))) {
				userService.modifyUserPwdByUserId(
						UserPwdMD5Encrypt.getPasswordByMD5Encrypt(newPwd),
						userId);
				retMap.put("error_code", "0");
			} else {
				retMap.put("error_code", "5");
				retMap.put("error_msg", "您输入的旧密码不正确，请检查！");
			}
			return retMap;
		} else {
			retMap.put("error_code", "4");
			retMap.put("error_msg", "服务器无法查询到您的电子邮件ID，因此无法修改密码，请联系系统管理员!");
			return retMap;
		}
	}
	
	@RequestMapping("/accounter/admin")
	public String getAccounterAdminHtml(HttpSession httpSession) {
		return "redirect:/views/webviews/accounter/admin.html";
	}
	
	@RequestMapping("/company/expenseAccountUpload")
	public String getCompanyAdminHtml(HttpSession httpSession) {
		return "redirect:/views/webviews/company/organization_information_settings.html";
	}
	
	@RequestMapping("/companyUser/expenseAccountUpload")
	public String getCompanyUserAdminHtml(HttpSession httpSession) {
		return "redirect:/views/webviews/companyUser/expense_account_upload.html";
	}
	
	@RequestMapping("/admin/admin")
	public String getAdminAdminHtml(HttpSession httpSession) {
		return "redirect:/views/webviews/admin/admin.html";
	}
}
