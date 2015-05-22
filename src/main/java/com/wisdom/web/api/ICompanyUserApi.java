package com.wisdom.web.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ICompanyUserApi {
	
	public Map<String, String> uploadCompanyUserBill(Map<String, String> params, MultipartFile file);
	
	public List<Map<String, String>> getCompanyUserInvoiceByStatus(Map<String, String> params);
	
	public Map<String, String> getUserInfo(String userId);
	
	public boolean updateInvoiceInfo(Map<String, String> params);
	
	public boolean submitInvoiceAudit(String userId, String idList);
	
	public boolean deleteInvoice(long invoiceId);
	
	public List<Map<String, Object>> getWaitInvoiceList(String userId);
	
	public List<Map<String, Object>> getFinishInvoiceList(String userId);
	
	public List<Map<String, Object>> getNeedAuditInvoiceList(String userId);
	
	public List<Map<String, Object>> getFinishAuditInvoiceList(String userId);
	
	public List<Map<String, Object>> getInvoiceHistory(String userId);
}
