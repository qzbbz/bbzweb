package com.wisdom.accounter.dao;

import java.util.List;

import com.wisdom.common.model.AccounterIndustry;

public interface IAccounterIndustryDao {

	public AccounterIndustry getAccounterIndustryById(long id);
	
	public List<AccounterIndustry> getAllAccounterIndustry();
	
	public boolean addAccounterIndustry(AccounterIndustry accounterIndustry);
	
	public boolean deleteAccounterIndustry(AccounterIndustry accounterIndustry);
	
	public boolean updateAccounterIndustry(AccounterIndustry accounterIndustry);
	
}
