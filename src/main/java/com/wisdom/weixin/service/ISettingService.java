package com.wisdom.weixin.service;

import java.util.Map;

public interface ISettingService {
	
	public Map<String, String> checkCompanyBind(String openId);

	public Map<String, String> userBindCompany(String openId, String inviteCode);
	
}
