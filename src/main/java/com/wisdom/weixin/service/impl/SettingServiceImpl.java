package com.wisdom.weixin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.UserOpenid;
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
	public Map<String, Object> checkCompanyBind(String openId, String type) {
		Map<String, Object> retMap = new HashMap<>();
		if (userWeixinService.userHasBindCompany(openId)) {
			List<UserOpenid> uoiList = userService.getUserIdByOpenId(openId);
			if(uoiList == null || uoiList.size() == 0) {
				retMap.put("bind_status", "not_bind");
			} else {
				UserOpenid uoi = uoiList.get(0);
				String userName = userService.getUserNameByUserId(uoi.getUserId());
				String userMsgEmail = userService.getUserMsgEmailByUserId(uoi.getUserId());
				for(UserOpenid oid : uoiList) {
					getCompanyNameAndDeptName(oid.getUserId(), retMap, type);
				}
				retMap.put("bind_status", "has_bind");
				retMap.put("userName", userName);
				retMap.put("userMsgEmail", userMsgEmail);
			}
		} else {
			retMap.put("bind_status", "not_bind");
		}
		logger.debug("retMap : {}", retMap);
		return retMap;
	}

	@Override
	public Map<String, Object> userBindCompany(String openId, String inviteCode) {
		Map<String, Object> retMap  = checkCompanyBind(openId, "0");
		if (("has_bind").equals(retMap.get("bind_status"))) {
			logger.info("retMap : {}", retMap.toString());
			return retMap;
		}
		
		String[] inviteCodeList = inviteCode.split(",");
		for(String subInviteCode : inviteCodeList) {
			String userId = userWeixinService.getUserIdByUserInviteCode(subInviteCode);
			if (userId == null || userId.isEmpty()) {
				retMap.put("invite_code_error", "invite code is wrong.");
				logger.info("retMap : {}", retMap.toString());
				return retMap;
			}
			userWeixinService.addOpenId(userId, openId);
			String userName = userService.getUserNameByUserId(userId);
			String userMsgEmail = userService.getUserMsgEmailByUserId(userId);
			getCompanyNameAndDeptName(userId, retMap, "0");
			retMap.put("userName", userName);
			retMap.put("userMsgEmail", userMsgEmail);
		}
		
		/*String userId = userWeixinService.getUserIdByUserInviteCode(inviteCode);
		int userTpyeId = userService.getUserTypeIdByUserId(userId);
		if (userId == null || userId.isEmpty()) {
			retMap.put("invite_code_error", "invite code is wrong.");
			logger.info("retMap : {}", retMap.toString());
			return retMap;
		}
		userWeixinService.addOpenId(userId, openId);
		String userName = userService.getUserNameByUserId(userId);
		String userMsgEmail = userService.getUserMsgEmailByUserId(userId);
		retMap = getCompanyNameAndDeptName(userId);
		retMap.put("userName", userName);
		retMap.put("userMsgEmail", userMsgEmail);
		if(userTpyeId == 2) {
			retMap.put("deptName", "公司系统管理员(不属于任何部门)");
		}*/
		logger.info("retMap : {}", retMap.toString());
		return retMap;
	}

	private void getCompanyNameAndDeptName(String userId, Map<String, Object> retMap, String type) {
		logger.debug("userId : {}", userId);
		long companyId = userService.getCompanyIdByUserId(userId);
		String adminUserId = userService.getCompanyAdminUserByCompanyId(companyId).getUserId();
		logger.debug("comapnyId : {}", companyId);
		String companyName = companyService.getCompanyName(companyId);
		long deptId = userDeptService.getDeptIdByUserId(userId);
		logger.debug("deptId : {}", deptId);
		String deptName = deptService.getDeptNameById(deptId);
		if(retMap.containsKey("companyName")) {
			Map<String, String> map = new HashMap<>();
			map.put("value", userId);
			if(("1").equals(type)) {
				map.put("admin", adminUserId);
			}
			map.put("text", companyName);
			((List<Map<String, String>>)retMap.get("companyName")).add(map);
		} else {
			List<Map<String, String>> list = new ArrayList<>();
			Map<String, String> map = new HashMap<>();
			map.put("value", userId);
			if(("1").equals(type)) {
				map.put("admin", adminUserId);
			}
			map.put("text", companyName);
			list.add(map);
			retMap.put("companyName", list);
		}
		if(retMap.containsKey("deptName")) {
			((List<String>)retMap.get("deptName")).add(deptName);
		} else {
			List<String> list  = new ArrayList<>();
			list.add(deptName);
			retMap.put("deptName", list);
		}
	}

	@Override
	public Map<String, String> updateUserInfo(String openId, String name,
			String email) {
		Map<String, String> retMap = new HashMap<>();
		List<UserOpenid> openidList = userService.getUserIdByOpenId(openId);
		for(UserOpenid uoi : openidList) {
			String userId = uoi.getUserId();
			if(userId == null || userId.isEmpty()) {
				continue;
			}
			if(name == null || name.isEmpty()) {
				String uName = userService.getUserNameByUserId(userId);
				retMap.put("userName", uName == null ? "" : uName);
			} else {
				if(!userService.setUserNameByUserId(name, userId)) {
					continue;
				} else {
					retMap.put("userName", name);
				}
			}
			if(email == null || email.isEmpty()) {
				String uEmail = userService.getUserMsgEmailByUserId(userId);
				retMap.put("userMsgEmail", uEmail == null ? "" : uEmail);
			} else {
				if(!userService.updateUserMsgEmailByUserId(email, userId)) {
					continue;
				} else {
					retMap.put("userMsgEmail", email);
				}
			}
			retMap.put("error_code", "0");
		}
		return retMap;
	}

	@Override
	public boolean userDisbindCompany(String openId) {
		return userWeixinService.deleteUserOpenidByOpenid(openId);
	}

}