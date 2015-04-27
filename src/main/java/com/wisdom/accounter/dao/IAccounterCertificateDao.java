package com.wisdom.accounter.dao;

import java.util.List;

import com.wisdom.common.model.AccounterCertificate;

public interface IAccounterCertificateDao {

	public AccounterCertificate getAccounterCertificateById(long id);
	
	public List<AccounterCertificate> getAllAccounterCertificate();
	
	public boolean addAccounterCertificate(AccounterCertificate accounterCertificate);
	
	public boolean deleteAccounterCertificate(AccounterCertificate accounterCertificate);
	
	public boolean updateAccounterCertificate(AccounterCertificate accounterCertificate);
	
}
