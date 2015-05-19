package com.wisdom.company.dao;

import com.wisdom.common.model.CompanyBankSta;

public interface ICompanyBankStaDao {
	
	public CompanyBankSta getCompanyBankStaByCompanyId(long companyId);
	
	public long addCompanyBankSta(CompanyBankSta companyBankSta);
	
	public boolean deleteCompanyBankStaByCompanyId(long companyId);
	
	public boolean updateCompanyBankSta(CompanyBankSta companyBankSta);
	
	public boolean updateCompanyBankStaIdentifyStatusById(long id, int status);
	
}
