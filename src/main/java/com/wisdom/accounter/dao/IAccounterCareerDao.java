package com.wisdom.accounter.dao;

import java.util.List;

import com.wisdom.common.model.AccounterCareer;

public interface IAccounterCareerDao {

	public AccounterCareer getAccounterCareerById(long id);                                                                                                                                                                
	
	public List<AccounterCareer> getAllAccounterCareer();
	
	public boolean addAccounterCareer(AccounterCareer accounterCareer);
	
	public boolean deleteAccounterCareer(AccounterCareer accounterCareer);
	
	public boolean updateAccounterCareer(AccounterCareer accounterCareer);
	
}
