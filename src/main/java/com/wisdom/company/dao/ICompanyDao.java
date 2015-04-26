package com.wisdom.company.dao;

import com.wisdom.common.model.Company;

public interface ICompanyDao {
	
	public Company getCompanyByCompanyId(long companyId);
	
	public boolean addCompany(Company company);
	
	public boolean deleteCompany(Company company);
	
	public boolean updateCompany(Company company);
	
}
