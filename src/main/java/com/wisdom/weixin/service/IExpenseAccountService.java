package com.wisdom.weixin.service;

import java.util.Map;

public interface IExpenseAccountService {
	
	Map<String, Map<String, String>> getBillsByOpenId(String openId);
	
	public String downloadFromUrl(String mediaId);
	
}
