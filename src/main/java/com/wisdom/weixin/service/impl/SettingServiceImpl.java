package com.wisdom.weixin.service.impl;

import java.util.HashMap;
import java.util.Map;

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
		if(userWeixinService.userHasBindCompany(openId)) {
			String userId = userService.getUserIdByOpenId(openId);
			retMap = getCompanyNameAndDeptName(userId);
		}
		return retMap;
	}

	@Override
	public Map<String, String> userBindCompany(String openId, String inviteCode) {
		Map<String, String> retMap = new HashMap<>();
		retMap = checkCompanyBind(openId);
		if(retMap.size() > 0) return retMap;
		String userId = userWeixinService.getUserIdByUserInviteCode(inviteCode);
		if(userId == null || userId.isEmpty()) return retMap;
		retMap = getCompanyNameAndDeptName(userId);
		return retMap;
	}
	
	private Map<String, String> getCompanyNameAndDeptName(String userId) {
		Map<String, String> retMap = new HashMap<>();
		long companyId = userService.getCompanyIdByUserId(userId);
		String companyName = companyService.getCompanyName(companyId);
		long deptId = userDeptService.getDeptIdByUserId(userId);
		String deptName = deptService.getDeptNameById(deptId);
		retMap.put("companyName", companyName);
		retMap.put("deptName", deptName);
		return retMap;
	}

}