package com.wisdom.weixin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.company.service.ICompanyService;
import com.wisdom.company.service.IDeptService;
import com.wisdom.user.service.IUserDeptService;
import com.wisdom.user.service.IUserService;
import com.wisdom.user.service.IUserWeixinService;
import com.wisdom.weixin.service.ISettingService;

@Service("weixinSettingService")
public class SettingServiceImpl implements ISettingService {

	private static final Logger logger = LoggerFactory
			.getLogger(SettingServiceImpl.class);

	@Autowired
	private IUserWeixinService userWeixinService;

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserDeptService userDeptService;

	@Autowired
	private IDeptService deptService;

	@Override
	public Map<String, String> checkCompanyBind(String openId) {
		Map<String, String> retMap = new HashMap<>();
		if (userWeixinService.userHasBindCompany(openId)) {
			String userId = userService.getUserIdByOpenId(openId);
			retMap = getCompanyNameAndDeptName(userId);
			retMap.put("bind_status", "has_bind");
		} else {
			retMap.put("bind_status", "not_bind");
		}
		logger.debug("retMap : {}", retMap);
		return retMap;
	}

	@Override
	public Map<String, String> userBindCompany(String openId, String inviteCode) {
		Map<String, String> retMap  = checkCompanyBind(openId);
		if (("has_bind").equals(retMap.get("bind_status"))) {
			logger.info("retMap : {}", retMap.toString());
			return retMap;
		}
		String userId = userWeixinService.getUserIdByUserInviteCode(inviteCode);
		if (userId == null || userId.isEmpty()) {
			retMap.put("invite_code_error", "invite code is wrong.");
			logger.info("retMap : {}", retMap.toString());
			return retMap;
		}
		userWeixinService.addOpenId(userId, openId);
		retMap = getCompanyNameAndDeptName(userId);
		logger.info("retMap : {}", retMap.toString());
		return retMap;
	}

	private Map<String, String> getCompanyNameAndDeptName(String userId) {
		Map<String, String> retMap = new HashMap<>();
		logger.debug("userId : {}", userId);
		long companyId = userService.getCompanyIdByUserId(userId);
		logger.debug("comapnyId : {}", companyId);
		String companyName = companyService.getCompanyName(companyId);
		long deptId = userDeptService.getDeptIdByUserId(userId);
		logger.debug("deptId : {}", deptId);
		String deptName = deptService.getDeptNameById(deptId);
		retMap.put("companyName", companyName);
		retMap.put("deptName", deptName);
		return retMap;
	}

}