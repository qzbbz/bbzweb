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
	
}
