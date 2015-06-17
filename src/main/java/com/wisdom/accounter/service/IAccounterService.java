package com.wisdom.accounter.service;

import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Accounter;

public interface IAccounterService {

	public Map<Long, String> getAllAccounterCareer();
	
	public Map<Long, String> getAllAccounterCertificate();
	
	public Map<Long, String> getAllAccounterIndustry();
	
	public Map<String, String> getAccounterByUserId(String userId);
	
	public List<Map<String, String>> getAllAccounter();
	
	public List<Map<String, String>> getAllAccounterByCondition(String province, String city, String area,
			String industry, String career);
	
	public boolean updateAccounter(Accounter accounter);
	
	public boolean addAccounter(Accounter accounter);
	
	public Map<String, List<Map<String, String>>> getAllCompanyExpense(String userId);
	
	public Map<String, List<Map<String, String>>> getCompanyExpenseByCompanyName(String userId, String companyName);
	
	public Map<String, String> accounterBelongToCompany(String userId);
	
	public Map<String, String> accounterHasFinishRegister(String userId);
	
}
