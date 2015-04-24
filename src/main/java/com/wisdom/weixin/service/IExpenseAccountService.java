package com.wisdom.weixin.service;

import java.util.Map;

public interface IExpenseAccountService {
	
	public Map<String, String> checkBindCompany(String openId);
	
	public Map<String, String> bindCompany(String openId);
}
