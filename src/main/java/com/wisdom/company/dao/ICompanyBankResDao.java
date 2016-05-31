package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.CompanyBankRes;

public interface ICompanyBankResDao {
	
	public CompanyBankRes getCompanyBankResById(long id);
	
	public List<CompanyBankRes> getAllCompanyBankRes(long companyId);
	
	public List<CompanyBankRes> getAllCompanyBankResByDate(long companyId, String date);
	
	public List<CompanyBankRes> getAllCompanyBankResByIdentifyStatus(long companyId, int status);
	
	public long addCompanyBankRes(CompanyBankRes CompanyBankRes);
	
	public boolean deleteCompanyBankResById(long id);
	
	public boolean updateCompanyBankRes(CompanyBankRes CompanyBankRes);
	
	public boolean updateCompanyBankResIdentifyStatusById(long id, int status);
	
}
