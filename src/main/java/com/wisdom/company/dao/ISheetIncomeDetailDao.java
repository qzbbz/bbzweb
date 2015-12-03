package com.wisdom.company.dao;

import java.util.List;
import java.util.Map;

import com.wisdom.common.model.SheetIncomeDetail;

public interface ISheetIncomeDetailDao {
	
	public List<SheetIncomeDetail> operateSqlByYear(String date,String[] porjects,long companyId);
	
	public  StringBuilder disposePorjectCondition(String[] porjects);
	
	public List<String> disposePorjectValue(List<String> newList,String[] porjects,String date);
	
	public  List<SheetIncomeDetail> operateSqlByMonth(String date,String[] porjects,long companyId);

}
