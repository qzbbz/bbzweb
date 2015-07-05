package com.wisdom.company.dao;

import com.wisdom.common.model.SheetBalance;

public interface ISheetBalanceDao {
	
	public SheetBalance getSheetBalanceDateByCompanyIdAndDate(long companyId, String date);
	
	public SheetBalance getNewestSheetBalanceDateByCompanyId(long companyId);
		
}
