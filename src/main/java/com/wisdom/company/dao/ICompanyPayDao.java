package com.wisdom.company.dao;

import java.sql.Timestamp;

import com.wisdom.common.model.CompanyPay;

public interface ICompanyPayDao {
	
	public CompanyPay getCompanyPayByCompanyIdAndPayStatus(long companyId, int status);
	
	public CompanyPay getCompanyPayByCompanyId(long companyId);
	
	public long addCompanyPay(CompanyPay companyPay);
	
	public boolean deleteCompanyPayByCompanyId(long companyId);
	
	public boolean updateCompanyPayPayStatusByCompanyId(long companyId, int status);
	
	public boolean updateCompanyOrderNoByCompanyId(long companyId, String orderNo);
	
	public boolean updateCompanyPayStatusAndTimeByOrderNo(String orderNo, int status, Timestamp time);
	
	public boolean updateCompanyPayByCompanyId(Long companyId, Double amount, String orderNo, int serviceTime);
	
}
