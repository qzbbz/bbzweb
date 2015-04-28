package com.wisdom.web.service;

import java.util.Map;

import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.Company;

public interface IUserModifyInfoService {

	public Map<String, String> accounterModifyInfo(String userId, Accounter accounter);
	
	public Map<String, String> companyModifyInfo(String userId, Company company);
	
}
