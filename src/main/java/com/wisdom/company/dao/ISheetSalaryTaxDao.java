package com.wisdom.company.dao;

import com.wisdom.common.model.SheetSalaryTax;

public interface ISheetSalaryTaxDao {
	
	public SheetSalaryTax getSheetSalaryTaxDateByCompanyIdAndDate(long companyId, String date);
	
	public SheetSalaryTax getNewestSheetSalaryTaxDateByCompanyId(long companyId);
		
}
