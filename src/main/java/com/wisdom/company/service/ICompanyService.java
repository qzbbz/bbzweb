package com.wisdom.company.service;

import java.util.List;

import com.wisdom.common.model.Company;
import com.wisdom.common.utils.Result;

public interface ICompanyService {
	
	public long addCompany(Company company);
	
	public int accounterAmountBelongToCompany(long companyId);
	
	public boolean companySelectAccounter(long companyId, String userId);
	
	public String getCompanyName(long companyId);
	
	public boolean updateCompanyName(String companyName, long companyId);
	
	public String getParentCompanyNameByCompanyId(long companyId);
	
	public List<Company> getSubCompanyListByCompanyId(long companyId);
	public Company getCompanyByCompanyId(Long id);
	
	public Result delCompany(long companyId);
	
	List<Company> getAllCompany();
}
