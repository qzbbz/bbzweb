package com.wisdom.company.service;

import java.util.List;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanySales;

public interface ICompanySalesService {
	
	public CompanySales getCompanySalesById(long id);
	
	public List<CompanySales> getAllCompanySales(long companyId);
	
	public List<CompanySales> getAllCompanySalesByDate(long companyId, String date);
	
	public long addCompanySales(CompanySales companySales);
	
	public boolean deleteCompanySalesById(long id);
	
	public boolean updateCompanySales(double amount, String type, long id);
	
}
