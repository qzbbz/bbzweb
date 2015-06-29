package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.Company;

public interface ICompanyDao {
	
	public Company getCompanyByCompanyId(long companyId);
	
	public long addCompany(Company company);
	
	public boolean deleteCompany(Company company);
	
	public boolean updateCompany(Company company);
	
	public boolean updateCompanyName(String companyName, long companyId);
	
	List<Company> getSubCompanyListByCompanyId(long companyId);
	
	List<Company> getAllCompany();
		
}
