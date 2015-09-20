package com.wisdom.company.dao;

import com.wisdom.common.model.CompanyPay;

public interface ICompanyPayDao {
	
	public CompanyPay getCompanyPayByCompanyIdAndPayStatus(long companyId, int status);
	
	public long addCompanyPay(CompanyPay companyPay);
	
	public boolean deleteCompanyPayByCompanyId(long companyId);
	
	public boolean updateCompanyPayPayStatusByCompanyId(long companyId, int status);
	
	public boolean updateCompanyOrderNoByCompanyId(long companyId, String orderNo);
	
	public boolean updateCompanyPayStatusByOrderNo(String orderNo, int status);
	
}
