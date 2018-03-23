package com.wisdom.company.dao;

import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyInfo;

public interface ICompanyDao {
	
	public Company getCompanyByCompanyId(long companyId);
	
	public List<Company> getCompanyByName(String companyName);
	
	public List<Company> getCompanyAndAccounter();
	
	public boolean updateAccounterForCompany(long companyId,String accounterId, String taxpayer_type, String invoice_amount);
	
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

	public List<Map<String, Object>> getCompanyAndAccounterByKey(String key);

	public List<Map<String, Object>> getComByIndexAndKey(int i, int length, String key);

	public List<Map<String, Object>> getComPayTotalPageByKey(String key);

	public List<Map<String, Object>> getComPayInfoByIndexAndKey(int i, int length, String key);
		
}
