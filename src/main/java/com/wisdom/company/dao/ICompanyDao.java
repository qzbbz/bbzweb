package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyInfo;

public interface ICompanyDao {
	
	public Company getCompanyByCompanyId(long companyId);
	
	public List<Company> getCompanyByName(String companyName);
	
	public List<Company> getCompanyAndAccounter();
	
	public boolean updateAccounterForCompany(long companyId,String accounterId);
	
	public long addCompany(Company company);
	
	public boolean deleteCompany(Company company);
	
	public boolean updateCompany(Company company);
	
	public boolean updateCompanyTakeType(long companyId, String takeType);
	
	public boolean updateCompanyName(String companyName, long companyId);
	
	public boolean updateCompanyAccounter(long companyId, String accounterId);
	
	List<Company> getSubCompanyListByCompanyId(long companyId);
	
	List<Company> getCompanyListByAccounterId(String accounterId);
	public List<Company> getSubCompanyListByCompanyIdOrder(long companyId);
	
	List<Company> getAllCompany();
	
	List<CompanyInfo> getCompanyInfoAndUserIDAndPhone();
		
}
