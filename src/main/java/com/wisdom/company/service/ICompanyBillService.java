package com.wisdom.company.service;

import java.util.List;

import com.wisdom.common.model.CompanyBill;

public interface ICompanyBillService {
	
	public CompanyBill getCompanyBillById(long id);
	
	public List<CompanyBill> getAllCompanyBillByDate(long companyId, String date);
	
	public long addCompanyBill(CompanyBill companyBill);
	
	public boolean deleteCompanyBillById(long id);
	
	public boolean updateCompanyBill(double amount, String type, long id, String supplyName, int isFixedAssets);
	
}
