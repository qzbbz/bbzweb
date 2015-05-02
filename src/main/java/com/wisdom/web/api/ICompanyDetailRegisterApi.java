package com.wisdom.web.api;

import java.util.Map;

import com.wisdom.common.model.Company;

public interface ICompanyDetailRegisterApi {

	public Map<Integer, String> companyDetailRegister(String userId, Company company);
		
}
