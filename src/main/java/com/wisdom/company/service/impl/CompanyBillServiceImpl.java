package com.wisdom.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.company.dao.ICompanyBillDao;
import com.wisdom.company.service.ICompanyBillService;

@Service("companyBillService")
public class CompanyBillServiceImpl implements ICompanyBillService {

	@Autowired
	private ICompanyBillDao companyBillDao;
	
	@Override
	public CompanyBill getCompanyBillById(long id) {
		return companyBillDao.getCompanyBillById(id);
	}

	@Override
	public List<CompanyBill> getAllCompanyBillByDate(long companyId, String date) {
		return companyBillDao.getAllCompanyBillByDate(companyId, date);
	}

	@Override
	public long addCompanyBill(CompanyBill companyBill) {	
		return companyBillDao.addCompanyBill(companyBill);
	}

	@Override
	public boolean deleteCompanyBillById(long id) {
		return companyBillDao.deleteCompanyBillById(id);
	}

	@Override
	public boolean updateCompanyBill(double amount, String type, long id, String supplyName, int isFixedAssets) {
		return companyBillDao.updateCompanyBill(amount, type, id, supplyName, isFixedAssets);
	}

}
