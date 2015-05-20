package com.wisdom.invoice.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wisdom.common.model.InvoiceInfo;
import com.wisdom.common.model.UserInvoice;
import com.wisdom.invoice.dao.IUserInvoiceDao;
import com.wisdom.invoice.dao.impl.UserInvoiceDaoImpl;
import com.wisdom.invoice.service.IUserInvoiceService;

@Service("userInvoiceService")
public class UserInvoiceServiceImpl implements IUserInvoiceService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserInvoiceServiceImpl.class);
	
	@Autowired
	private IUserInvoiceDao userInvoiceDao; 

	@Override
	public UserInvoice getUserInvoiceByInvoiceId(long longId) {
		return userInvoiceDao.getUserInvoiceByInvoiceId(longId);
	}
	
	@Override
	public boolean updateInvoiceApprovalStatus(String userId,String approvalUserId,long invoiceId,int status,int approvalStatus){
		UserInvoice userInvoice = new UserInvoice();
		userInvoice.setInvoiceId(invoiceId);
		userInvoice.setStatus(status);
		userInvoice.setApprovalStatus(approvalStatus);
		userInvoice.setUserId(userId);
		userInvoice.setApprovalId(approvalUserId);
		userInvoice.setUpdateTime(new Timestamp(new Date().getTime()));
		return userInvoiceDao.updateUserInvoice(userInvoice);
	}

	@Override
	public List<UserInvoice> getUserInvoiceByUserId(String userId) {
		return userInvoiceDao.getUserInvoiceByUserId(userId);
	}
	
	@Override
	public boolean addUserInvoiceRecord(long invoiceId,String userId,String receiver,int status) {
		UserInvoice userInvoice = new UserInvoice();
		userInvoice.setInvoiceId(invoiceId);
		userInvoice.setStatus(status);
		userInvoice.setUserId(userId);
		userInvoice.setApprovalId(receiver);
		userInvoice.setCreateTime(new Timestamp(new Date().getTime()));
		return userInvoiceDao.addUserInvoice(userInvoice);
	}

	@Override
	public UserInvoice getUserInvoiceByUserIdAndInvoiceId(String userId,
			long invoiceId) {
		return userInvoiceDao.getUserInvoiceByUserIdAndInvoiceId(userId, invoiceId);
	}

	@Override
	public List<InvoiceInfo> getInvoiceInfoByCondition(String userId,
			String date, String submitter, Double amount, Integer expenseType,Integer page,Integer pageSize){
		if(StringUtils.isEmpty(userId)){
			logger.error("input userId empty error");
			return null;
		}
		return userInvoiceDao.getInvoiceInfoByCondition(userId, date, submitter, amount, 
				expenseType, page, pageSize);
	}
}
