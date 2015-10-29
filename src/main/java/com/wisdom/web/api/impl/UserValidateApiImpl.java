package com.wisdom.web.api.impl;

import java.net.Socket;
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
import com.wisdom.dispatch.service.impl.JavaMailService;
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
	private JavaMailService javaMailService;
	
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
				String mailSubject = "欢迎您注册帮帮账";
				String mailBody = "尊敬的用户您好！<br>非常欢迎您注册帮帮账，您当前注册的用户名是："+ userId +"，密码是："+userPwd + "。<br>请您妥善保管账号信息！<br>本邮件是系统自动发送的邮件，请勿回复！<br><br>上海元升财务咨询有限公司<br>上海市杨浦区国定东路200号1号楼514室<br>联系电话:4000-866-018";
				javaMailService.sendMailOut(userId, mailSubject, mailBody, "");
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

	@Override
	public int getUserAuditStatusByUserId(String userId) {
		return userService.getUserAuditStatusByUserId(userId);
	}

	@Override
	public Map<String, String> UserForgetPwd(String userId) {
		Map<String, String> retMap = new HashMap<>();
		String newPwd = UserPwdMD5Encrypt.getPasswordByMD5Encrypt("123456");
		if(userService.modifyUserPwdByUserId(newPwd, userId)) {
			retMap.put("error_code", "0");
			retMap.put("error_message", "");
			String mailSubject = "帮帮账-忘记密码";
			String mailBody = "尊敬的用户您好！<br>您当前注册的用户名是："+ userId +"，密码是：123456。<br>请您妥善保管账号信息！<br>本邮件是系统自动发送的邮件，请勿回复！<br><br>上海元升财务咨询有限公司<br>上海市杨浦区国定东路200号1号楼514室<br>联系电话:4000-866-018";
			javaMailService.sendMailOut(userId, mailSubject, mailBody, "");
		} else {
			retMap.put("error_code", "1");
			retMap.put("error_message", "重置密码失败");
		}
		return retMap;
	}
}
