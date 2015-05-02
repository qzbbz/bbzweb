package com.wisdom.weixin.service;

import java.util.List;
import java.util.Map;

public interface IExpenseAccountService {
	
	Map<String, List<Map<String, String>>> getBillsByOpenId(String openId);
	
	boolean approvalBill(String approvalId, String invoiceId, String userId);
	
	public String downloadFromUrl(String mediaId, String openId);
	
}
