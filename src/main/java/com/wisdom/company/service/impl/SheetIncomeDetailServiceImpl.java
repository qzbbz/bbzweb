package com.wisdom.company.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wisdom.common.model.SheetIncomeDetail;
import com.wisdom.company.dao.ISheetIncomeDetailDao;
import com.wisdom.company.service.ISheetIncomeDetailService;
@Service("sheetIncomeDetailService")
public class SheetIncomeDetailServiceImpl implements ISheetIncomeDetailService {
	@Autowired
	private ISheetIncomeDetailDao sheetIncomeDetailDao;
	@Override
	public  List<SheetIncomeDetail> InvokingOperateSqlByYear(String date, String[] porjects,long companyId) {
		return sheetIncomeDetailDao.operateSqlByYear(date, porjects,companyId);
	}
	@Override
	public  List<SheetIncomeDetail> InvokingOperateSqlByMonth(String date, String[] porjects,long companyId) {
		return sheetIncomeDetailDao.operateSqlByMonth(date, porjects,companyId);
	}



}
