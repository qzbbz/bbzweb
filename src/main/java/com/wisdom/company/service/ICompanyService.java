package com.wisdom.company.service;

import com.wisdom.common.model.Company;

public interface ICompanyService {
	
	public long addCompany(Company company);
	
	public int accounterAmountBelongToCompany(long companyId);
	
	public boolean companySelectAccounter(long companyId, String userId);
	
	public String getCompanyName(long companyId);
	
}
