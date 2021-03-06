package com.wisdom.company.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CompanyAndPayModel;
import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanyPay;
import com.wisdom.common.model.CompanyPayHistory;
import com.wisdom.company.dao.ICompanyBillDao;
import com.wisdom.company.dao.ICompanyPayDao;
import com.wisdom.company.dao.ICompanyPayHistoryDao;
import com.wisdom.company.service.ICompanyBillService;
import com.wisdom.company.service.ICompanyPayService;

@Service("companyPayService")
public class CompanyPayServiceImpl implements ICompanyPayService {

	@Autowired
	private ICompanyPayDao companyPayDao;
	
	@Autowired
	private ICompanyPayHistoryDao companyPayHistoryDao;

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
		if(status != 1){
			return false;
		}
		CompanyPay companyPay = companyPayDao.getCompanyPayByOrderNo(orderNo);
		Timestamp currentExpiredTime = companyPay.getExpiredTime();
		Date date= new Date();
		Timestamp now = new Timestamp(date.getTime());
		Calendar c = Calendar.getInstance();
		if(currentExpiredTime != null && now.before(currentExpiredTime)){
			c.setTime(currentExpiredTime);
			c.add(Calendar.MONTH, companyPay.getServiceTime()); 
		}else{
			c.setTime(now);
			
			c.add(Calendar.MONTH, companyPay.getServiceTime()); 
		}
		if(companyPay.getPayStatus() != 1){
			Timestamp newExpiredTime = new Timestamp(0);
			newExpiredTime.setTime(c.getTime().getTime());
			//Add record to company_pay_history
			CompanyPayHistory companyPayHistory = new CompanyPayHistory();
			companyPayHistory.setApplyInvoice(companyPay.getApplyInvoice());
			companyPayHistory.setCompanyId(companyPay.getCompanyId());
			companyPayHistory.setContractFile(companyPay.getContractFile());
			companyPayHistory.setCreatedTime(now);
			companyPayHistory.setMailAddress(companyPay.getMailAddress());
			companyPayHistory.setOrderNo(orderNo);
			companyPayHistory.setPayAmount(companyPay.getPayAmount());
			companyPayHistory.setServiceTime(companyPay.getServiceTime());
			companyPayHistory.setPayStatus(status);
			companyPayHistoryDao.addCompanyHistoryPay(companyPayHistory);
			return companyPayDao.updateCompanyPayStatusAndTimeByOrderNo(orderNo, status, time, contractFile, newExpiredTime);
		}
		else{
			return false;
		}
	}

	@Override
	public CompanyPay getCompanyPayByCompanyId(long companyId) {
		// TODO Auto-generated method stub
		return companyPayDao.getCompanyPayByCompanyId(companyId);
	}

	@Override
	public boolean updateCompanyPayByCompanyId(Long companyId, Integer payStatus, Double amount, String orderNo, int serviceTime, Timestamp expiredTime, String location, Integer perMonth, String type) {
		// TODO Auto-generated method stub
		CompanyPay companyPay = companyPayDao.getCompanyPayByCompanyId(companyId);
		Timestamp newExpiredTime = new Timestamp(0);
		if(payStatus == 2){
			Timestamp currentExpiredTime = companyPay.getExpiredTime();
			java.util.Date date= new java.util.Date();
			Timestamp now = new Timestamp(date.getTime());
			Calendar c = Calendar.getInstance();
			if(currentExpiredTime != null && now.before(currentExpiredTime)){
				c.setTime(currentExpiredTime);
				c.add(Calendar.MONTH, serviceTime); 
			}else{
				c.setTime(now);
				c.add(Calendar.MONTH, serviceTime); 
			}

			newExpiredTime.setTime(c.getTime().getTime());
			
		}else{
			newExpiredTime = companyPay.getExpiredTime();
		}
		if(expiredTime != null){
			newExpiredTime = expiredTime;
		}
		return companyPayDao.updateCompanyPayByCompanyId(companyId, payStatus, amount, orderNo, serviceTime, newExpiredTime, location, perMonth, type);
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

	@Override
	public int addCompanyPayDetail(CompanyPay cp, String huisuanqingjiao, String huisuanqingjiao_expired_time,
			String gongshangnianjian, String gongshangnianjian_expired_time) {
		return companyPayDao.addCompanyPayDetail(cp, huisuanqingjiao, huisuanqingjiao_expired_time, gongshangnianjian, gongshangnianjian_expired_time);
	}

	@Override
	public boolean updateCompanyPayDetailByCompanyId(Long companyId, Integer payStatus, Double amount, String orderNo,
			int serviceTime, Timestamp expiredTime, String location, Integer perMonth, String taxType,
			String huisuanqingjiao, String huisuanqingjiao_expired_time, String gongshangnianjian,
			String gongshangnianjian_expired_time) {
		CompanyPay companyPay = companyPayDao.getCompanyPayByCompanyId(companyId);
		Timestamp newExpiredTime = new Timestamp(0);
		if(payStatus == 2){
			Timestamp currentExpiredTime = companyPay.getExpiredTime();
			java.util.Date date= new java.util.Date();
			Timestamp now = new Timestamp(date.getTime());
			Calendar c = Calendar.getInstance();
			if(currentExpiredTime != null && now.before(currentExpiredTime)){
				c.setTime(currentExpiredTime);
				c.add(Calendar.MONTH, serviceTime); 
			}else{
				c.setTime(now);
				c.add(Calendar.MONTH, serviceTime); 
			}

			newExpiredTime.setTime(c.getTime().getTime());
			
		}else{
			newExpiredTime = companyPay.getExpiredTime();
		}
		if(expiredTime != null){
			newExpiredTime = expiredTime;
		}
		return companyPayDao.updateCompanyPayDetailByCompanyId(companyId, payStatus, amount, orderNo, 
				serviceTime, newExpiredTime, location, perMonth, taxType, huisuanqingjiao, huisuanqingjiao_expired_time,
				gongshangnianjian, gongshangnianjian_expired_time);
	}

}
