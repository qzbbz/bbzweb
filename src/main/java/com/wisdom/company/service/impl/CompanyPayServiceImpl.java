package com.wisdom.company.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CompanyAndPayModel;
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
	public boolean updateCompanyPayStatusAndTimeByOrderNo(String orderNo, int status, Timestamp time, String contractFile) {
		// TODO Auto-generated method stub
		return companyPayDao.updateCompanyPayStatusAndTimeByOrderNo(orderNo, status, time, contractFile);
	}

	@Override
	public CompanyPay getCompanyPayByCompanyId(long companyId) {
		// TODO Auto-generated method stub
		return companyPayDao.getCompanyPayByCompanyId(companyId);
	}

	@Override
	public boolean updateCompanyPayByCompanyId(Long companyId, Integer payStatus, Double amount, String orderNo, int serviceTime) {
		// TODO Auto-generated method stub
		return companyPayDao.updateCompanyPayByCompanyId(companyId, payStatus, amount, orderNo, serviceTime);
	}

	@Override
	public boolean updateApplyInvoiceByCompanyId(Long companyId, int applyInvoice, String address) {
		// TODO Auto-generated method stub
		return companyPayDao.updateApplyInvoiceByCompanyId(companyId, applyInvoice, address);
	}

	@Override
	public CompanyPay getCompanyPayByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return companyPayDao.getCompanyPayByOrderNo(orderNo);
	}

	@Override
	public boolean updateCompanyPayStatusToTrial(Long companyId) {
		// TODO Auto-generated method stub
		return companyPayDao.updateCompanyPayStatusToTrial(companyId);
	}

	@Override
	public List<CompanyPay> getExpiredCompanyPay() {
		// TODO Auto-generated method stub
		return companyPayDao.getExpiredCompanyPay();
	}

	@Override
	public List<CompanyAndPayModel> getCompanyAndPayModel() {
		// TODO Auto-generated method stub
		return companyPayDao.getCompanyAndPayModel();
	}

	@Override
	public List<CompanyAndPayModel> getCompanyAndPayModelByCompanyName(String companyName) {
		// TODO Auto-generated method stub
		return companyPayDao.getCompanyAndPayModelByCompanyName(companyName);
	}

}
