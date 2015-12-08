package com.wisdom.company.service;
import java.util.List;

import com.wisdom.common.model.SheetIncomeDetail;
public interface ISheetIncomeDetailService {

	public  List<SheetIncomeDetail> InvokingOperateSqlByYear(String date,String[] porjects,long companyId);

	public	List<SheetIncomeDetail> InvokingOperateSqlByMonth(String date,String[] porjects,long companyId);	
}
