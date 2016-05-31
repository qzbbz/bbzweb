package com.wisdom.company.service;

import java.util.List;

import com.wisdom.common.model.CompanyBankRes;


public interface ICompanyBankResService {

	public CompanyBankRes getCompanyBankResById(long id);
	
	public long addCompanyBankRes(CompanyBankRes companyBankRes);
	
	public boolean deleteCompanyBankResByCompanyId(long companyId);
	
	public boolean updateCompanyBankRes(CompanyBankRes companyBankRes);
	
	public boolean updateCompanyBankResIdentifyStatusById(long id, int status);
	
	public List<CompanyBankRes> getAllCompanyBankRes(long companyId);
	
	public List<CompanyBankRes> getAllCompanyBankResByDate(long companyId, String date);
	
	public List<CompanyBankRes> getAllCompanyBankResByIdentifyStatus(long companyId, int status);
	
}
