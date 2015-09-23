package com.wisdom.web.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ICompanyBillApi {

	public Map<String, String> uploadCompanyBill(Map<String, String> params, MultipartFile file);
	
	public List<Map<String, String>> getCompanyBillByCondition(Map<String, String> params);
	
	public boolean deleteCompanyBill(String idList, String realPath);
	
	public boolean modifyCompanyBill(String id, String amount, String type, String supplyName, String isFixedAssets);
	
}