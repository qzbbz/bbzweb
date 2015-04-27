package com.wisdom.weixin.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.wisdom.weixin.service.IExpenseAccountService;

@Service("weixinExpenseAccountService")
public class ExpenseAccountServiceImpl implements IExpenseAccountService {

	@Override
	public Map<String, String> checkBindCompany(String openId) {
		return null;
	}

	@Override
	public Map<String, String> bindCompany(String openId) {
		return null;
	}
	
}
