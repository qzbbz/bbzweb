package com.wisdom.weixin.service;

import java.util.List;
import java.util.Map;

public interface IExpenseAccountService {
	
	Map<String, List<Map<String, Object>>> getNeedAuditBillsByOpenId(String openId);
	
	Map<String, List<Map<String, Object>>> getInboxBillsByOpenId(String openId);
	
	List<Map<String, String>> getAllExpenseType();
	
	boolean approvalBill(String approvalId, String invoiceId, String userId, int status);
	
	public boolean submitExpenseAccount(String openId,String image);
	
	public String downloadFromUrl(String mediaId, String openId);
	
	public String downloadFromUrl(Map<String, Object> params);
	
	public boolean submitBillAudit(String invoiceId);
	
}