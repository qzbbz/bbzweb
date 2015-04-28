package com.wisdom.web.service;

import java.util.Map;

import com.wisdom.common.model.Company;

public interface ICompanyDetailRegisterService {

	public Map<Integer, String> companyDetailRegister(String userId, Company company);
		
}
