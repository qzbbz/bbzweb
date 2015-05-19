package com.wisdom.web.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ICompanyApi {

	public Map<String, String> companyDetailRegister(Map<String, String> params);
	
	public Map<String, String> companyInfoSettings(MultipartFile[] files, Map<String, String> params);
	
	public Map<String, String> getSSSInfo(String userId, String cityName, String type);
	
	public Map<String, String> updateSSSInfo(Map<String, String> params);
	
	public ResponseEntity<byte[]> downloadSalarySocialSecurityTemplate(String userId, String cityName, String type, String realPath);
	
	public Map<String, String> checkTemplateExist(String userId, String cityName, String type);
	
	public Map<String, String> uploadCompanySalary(String userId, String realPath, MultipartFile file);
	
	public Map<String, String> uploadCompanyBankSta(MultipartFile file, Map<String, String> params);
	
	public List<Map<String, String>> getCompanyBankStaByCondition(Map<String, String> params);
		
}