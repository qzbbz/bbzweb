package com.wisdom.company.dao;

import com.wisdom.common.model.SheetIncome;

public interface ISheetIncomeDao {
	
	public SheetIncome getSheetIncomeDateByCompanyIdAndDate(long companyId, String date);
	
	public SheetIncome getNewestSheetIncomeDateByCompanyId(long companyId);
		
}
