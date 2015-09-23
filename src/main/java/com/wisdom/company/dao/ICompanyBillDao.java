package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.CompanyBill;

public interface ICompanyBillDao {
	
	public CompanyBill getCompanyBillById(long id);
	
	public List<CompanyBill> getAllCompanyBill(long companyId);
	
	public List<CompanyBill> getAllCompanyBillByDate(long companyId, String date);
	
	public long addCompanyBill(CompanyBill companyBill);
	
	public boolean deleteCompanyBillById(long id);
	
	public boolean updateCompanyBill(double amount, String type, long id, String supplyName, int isFixedAssets);
	
}
