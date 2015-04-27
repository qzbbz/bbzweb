package com.wisdom.accounter.dao;

import java.util.List;

import com.wisdom.common.model.Accounter;

public interface IAccounterDao {

	public Accounter getAccounterByUserId(String userId);
	
	public List<Accounter> getAccounterByCompanyId(long companyId);
	
	public boolean addAccounter(Accounter accounter);
	
	public boolean deleteAccounter(Accounter accounter);
	
	public boolean updateAccounter(Accounter accounter);
	
}
