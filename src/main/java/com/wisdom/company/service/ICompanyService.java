package com.wisdom.company.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyInfo;
import com.wisdom.common.utils.Result;

public interface ICompanyService {
	
	public long addCompany(Company company);
	
	public List<Company> getCompanyAndAccounter();
	
	public int accounterAmountBelongToCompany(long companyId);
	
	public boolean updateAccounterForCompany(long companyId, String accounterId);
	
	public boolean companySelectAccounter(long companyId, String userId);
	
	public String getCompanyName(long companyId);
	
	public boolean updateCompanyName(String companyName, long companyId);
	
	public String getParentCompanyNameByCompanyId(long companyId);
	
	public List<Company> getSubCompanyListByCompanyId(long companyId);
	public Company getCompanyByCompanyId(Long id);
	
	public Result delCompany(long companyId);
	
	List<Company> getAllCompany();
	
	public boolean updateCompanyTakeType(long companyId, String takeType);
	
	public boolean updateCompanyAccounter(long companyId, String accounterId);
	
	List<Company> getCompanyListByAccounterId(String accounterId);
	
	public List<Company> getCompanyByName(String companyName);
	
	public List<CompanyInfo> getCompanyInfoAndUserIDAndPhone();

	public List<Map<String, Object>> getCompanyAndAccounterByKey(String key);

	public List<Map<String, Object>> getComByIndexAndKey(int i, int length, String key);

	public List<Map<String, Object>> getComPayTotalPageByKey(String key);

	public List<Map<String, Object>> getComPayInfoByIndexAndKey(int i, int length, String key);
}
