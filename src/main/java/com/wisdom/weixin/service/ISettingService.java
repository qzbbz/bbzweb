package com.wisdom.weixin.service;

import java.util.Map;

public interface ISettingService {
	
	public Map<String, Object> checkCompanyBind(String openId, String type);

	public Map<String, Object> userBindCompany(String openId, String inviteCode);
	
	public boolean userDisbindCompany(String openId);
	
	public Map<String, String> updateUserInfo(String openId, String name, String email);
	
}
