package com.wisdom.weixin.service;

import java.util.List;
import java.util.Map;

public interface IExpenseAccountService {
	
	Map<String, List<Map<String, Object>>> getNeedAuditBillsByOpenId(String openId);
	
	List<Map<String, Object>> newGetNeedAuditBillsByOpenId(String openId);
	
	List<Map<String, Object>> getWaitAuditInvoices(String openId);
	
	List<Map<String, Object>> getFinishAuditInvoices(String openId);
	
	Map<String, List<Map<String, Object>>> getInboxBillsByOpenId(String openId);
	
	List<Map<String, String>> getAllExpenseType();
	
	boolean approvalBill(String approvalId, String invoiceId, String userId, int status, String reasons);
	boolean newApprovalBill(String invoiceId, String status, String reasons);
	
	public boolean submitExpenseAccount(String openId,String image);
	
	public String downloadFromUrl(String mediaId, String openId);
	
	public String downloadFromUrl(Map<String, Object> params);
	
	public boolean submitBillAudit(String openId, String invoiceId, Map<String, String> params);
	
}