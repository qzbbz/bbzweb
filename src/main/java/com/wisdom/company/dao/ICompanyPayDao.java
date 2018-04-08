package com.wisdom.company.dao;

import java.sql.Timestamp;
import java.util.List;

import com.wisdom.common.model.CompanyAndPayModel;
import com.wisdom.common.model.CompanyPay;

public interface ICompanyPayDao {
	
	public CompanyPay getCompanyPayByCompanyIdAndPayStatus(long companyId, int status);
	
	public CompanyPay getCompanyPayByCompanyId(long companyId);
	
	public CompanyPay getCompanyPayByOrderNo(String orderNo);
	
	public long addCompanyPay(CompanyPay companyPay);
	
	public boolean deleteCompanyPayByCompanyId(long companyId);
	
	public boolean updateCompanyPayPayStatusByCompanyId(long companyId, int status);
	
	public boolean updateCompanyOrderNoByCompanyId(long companyId, String orderNo);
	
	public boolean updateCompanyPayStatusAndTimeByOrderNo(String orderNo, int status, Timestamp time, String contractFile, Timestamp expiredTime);
	
	public boolean updateCompanyPayByCompanyId(Long companyId, Integer payStatus, Double amount, String orderNo, int serviceTime, Timestamp expiredTime,String location, Integer perMonth, String type);
	
	public boolean updateApplyInvoiceByCompanyId(Long companyId, int applyInvoice, String address);
	
	public boolean updateCompanyPayStatusToTrial(Long companyId);
	
	public List<CompanyPay> getExpiredCompanyPay();
	
	public List<CompanyAndPayModel> getCompanyAndPayModel();
	
	public List<CompanyAndPayModel> getCompanyAndPayModelByCompanyName(String companyName);

	public int addCompanyPayDetail(CompanyPay cp, String huisuanqingjiao, String huisuanqingjiao_expired_time,
			String gongshangnianjian, String gongshangnianjian_expired_time);

	public boolean updateCompanyPayDetailByCompanyId(Long companyId, Integer payStatus, Double amount, String orderNo,
			int serviceTime, Timestamp newExpiredTime, String location, Integer perMonth, String taxType,
			String huisuanqingjiao, String huisuanqingjiao_expired_time, String gongshangnianjian,
			String gongshangnianjian_expired_time);
	
}
