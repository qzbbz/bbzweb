package com.wisdom.accounter.service;

import java.util.Map;

import com.wisdom.common.model.Accounter;

public interface IAccounterService {

	public Map<Long, String> getAllAccounterCareer();
	
	public Map<Long, String> getAllAccounterCertificate();
	
	public Map<Long, String> getAllAccounterIndustry();
	
	public Map<String, String> getAccounterByUserId(String userId);
	
	public boolean updateAccounter(Accounter accounter);
	
}
