package com.wisdom.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanySales;
import com.wisdom.company.dao.ICompanyBillDao;
import com.wisdom.company.dao.ICompanySalesDao;
import com.wisdom.company.service.ICompanyBillService;
import com.wisdom.company.service.ICompanySalesService;

@Service("companySalesService")
public class CompanySalesServiceImpl implements ICompanySalesService {

	@Autowired
	private ICompanySalesDao companySalesDao;
	
	@Override
	public CompanySales getCompanySalesById(long id) {
		return companySalesDao.getCompanySalesById(id);
	}

	@Override
	public List<CompanySales> getAllCompanySales(long companyId) {
		return companySalesDao.getAllCompanySales(companyId);
	}

	@Override
	public List<CompanySales> getAllCompanySalesByDate(long companyId, String date) {
		return companySalesDao.getAllCompanySalesByDate(companyId, date);
	}

	@Override
	public long addCompanySales(CompanySales companyBill) {
		return companySalesDao.addCompanySales(companyBill);
	}

	@Override
	public boolean deleteCompanySalesById(long id) {
		return companySalesDao.deleteCompanySalesById(id);
	}

	@Override
	public boolean updateCompanySales(double amount, String type, long id) {
		return companySalesDao.updateCompanySales(amount, type, id);
	}

}
