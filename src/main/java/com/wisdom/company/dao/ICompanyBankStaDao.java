package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.CompanyBankSta;

public interface ICompanyBankStaDao {
	
	public CompanyBankSta getCompanyBankStaById(long id);
	
	public List<CompanyBankSta> getAllCompanyBankSta(long companyId);
	
	public List<CompanyBankSta> getAllCompanyBankStaByDate(long companyId, String date);
	
	public List<CompanyBankSta> getAllCompanyBankStaByIdentifyStatus(long companyId, int status);
	
	public long addCompanyBankSta(CompanyBankSta companyBankSta);
	
	public boolean deleteCompanyBankStaById(long id);
	
	public boolean updateCompanyBankSta(CompanyBankSta companyBankSta);
	
	public boolean updateCompanyBankStaIdentifyStatusById(long id, int status);
	
}
