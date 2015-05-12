package com.wisdom.invoice.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.UserInvoice;
import com.wisdom.invoice.dao.IUserInvoiceDao;
import com.wisdom.invoice.service.IUserInvoiceService;

@Service("userInvoiceService")
public class UserInvoiceServiceImpl implements IUserInvoiceService {
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

}
