package com.wisdom.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanyPay;
import com.wisdom.company.dao.ICompanyBillDao;
import com.wisdom.company.dao.ICompanyPayDao;
import com.wisdom.company.service.ICompanyBillService;
import com.wisdom.company.service.ICompanyPayService;

@Service("companyPayService")
public class CompanyPayServiceImpl implements ICompanyPayService {

	@Autowired
	private ICompanyPayDao companyPayDao;

	@Override
	public CompanyPay getCompanyPayByCompanyIdAndPayStatus(long companyId, int status) {
		// TODO Auto-generated method stub
		return companyPayDao.getCompanyPayByCompanyIdAndPayStatus(companyId, status);
	}

	@Override
	public long addCompanyPay(CompanyPay companyPay) {
		// TODO Auto-generated method stub
		return companyPayDao.addCompanyPay(companyPay);
	}

	@Override
	public boolean deleteCompanyPayByCompanyId(long companyId) {
		// TODO Auto-generated method stub
		return companyPayDao.deleteCompanyPayByCompanyId(companyId);
	}

	@Override
	public boolean updateCompanyPayPayStatusByCompanyId(long companyId, int status) {
		// TODO Auto-generated method stub
		return companyPayDao.updateCompanyPayPayStatusByCompanyId(companyId, status);
	}

	@Override
	public boolean updateCompanyOrderNoByCompanyId(long companyId, String orderNo) {
		// TODO Auto-generated method stub
		return companyPayDao.updateCompanyOrderNoByCompanyId(companyId, orderNo);
	}

	@Override
	public boolean updateCompanyPayStatusByOrderNo(String orderNo, int status) {
		// TODO Auto-generated method stub
		return companyPayDao.updateCompanyPayStatusByOrderNo(orderNo, status);
	}

}
