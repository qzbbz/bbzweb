package com.wisdom.company.dao;

import com.wisdom.common.model.SheetCash;

public interface ISheetCashDao {
	
	public SheetCash getSheetCashDateByCompanyIdAndDate(long companyId, String date);
	
	public SheetCash getNewestSheetCashDateByCompanyId(long companyId);
		
}
