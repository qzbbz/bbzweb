package com.wisdom.accounter.service;

import java.util.Map;

public interface IAccounterService {

	public Map<Long, String> getAllAccounterCareer();
	
	public Map<Long, String> getAllAccounterCertificate();
	
	public Map<Long, String> getAllAccounterIndustry();
	
	public Map<String, String> getAccounterByUserId(String userId);
	
}
