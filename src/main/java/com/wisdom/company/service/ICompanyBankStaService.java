package com.wisdom.company.service;

import java.util.List;

import com.wisdom.common.model.CompanyBankSta;

public interface ICompanyBankStaService {

	public CompanyBankSta getCompanyBankStaById(long id);
	
	public long addCompanyBankSta(CompanyBankSta companyBankSta);
	
	public boolean deleteCompanyBankStaByCompanyId(long companyId);
	
	public boolean updateCompanyBankSta(CompanyBankSta companyBankSta);
	
	public boolean updateCompanyBankStaIdentifyStatusById(long id, int status);
	
	public List<CompanyBankSta> getAllCompanyBankSta(long companyId);
	
	public List<CompanyBankSta> getAllCompanyBankStaByDate(long companyId, String date);
	
	public List<CompanyBankSta> getAllCompanyBankStaByIdentifyStatus(long companyId, int status);
	
}
