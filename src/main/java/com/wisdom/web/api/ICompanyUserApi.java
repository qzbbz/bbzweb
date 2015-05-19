package com.wisdom.web.api;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ICompanyUserApi {
	
	public Map<String, String> uploadCompanyUserBill(Map<String, String> params, MultipartFile file);
	
}
