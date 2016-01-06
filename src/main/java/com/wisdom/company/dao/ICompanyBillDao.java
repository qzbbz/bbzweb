package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.TestInvoiceRecord;

public interface ICompanyBillDao {
	
	//Used by com.wisdom.web.api.impl.CompanyBillApiImpl.deleteCompanyBill
	@Deprecated
	public CompanyBill getCompanyBillById(long id);
	
	public List<CompanyBill> getAllCompanyBillByDate(long companyId, String date);
	
	public long addCompanyBill(CompanyBill companyBill);
	
	public boolean deleteCompanyBillById(long id);
	
	public boolean updateCompanyBill(double amount, String type, long id, String supplyName, int isFixedAssets);
	
}
