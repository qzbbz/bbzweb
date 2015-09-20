package com.wisdom.company.service;

import java.util.List;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanyPay;

public interface ICompanyPayService {
	
public CompanyPay getCompanyPayByCompanyIdAndPayStatus(long companyId, int status);
	
	public long addCompanyPay(CompanyPay companyPay);
	
	public boolean deleteCompanyPayByCompanyId(long companyId);
	
	public boolean updateCompanyPayPayStatusByCompanyId(long companyId, int status);
	
	public boolean updateCompanyOrderNoByCompanyId(long companyId, String orderNo);
	
	public boolean updateCompanyPayStatusByOrderNo(String orderNo, int status);
	
}
