package com.wisdom.invoice.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.InvoiceApproval;
import com.wisdom.invoice.dao.IInvoiceApprovalDao;
import com.wisdom.invoice.service.IInvoiceApprovalService;

@Service("invoiceApprovalService")
public class InvoiceApprovalServiceImpl implements IInvoiceApprovalService {
	@Autowired
	private IInvoiceApprovalDao invoiceApprovalDao;
	
	@Override
	public List<InvoiceApproval> getInvoiceApprovalListByUserId(String userId) {
		return invoiceApprovalDao.getInvoiceApprovalListByUserId(userId);
	}

	@Override
	public boolean updateApprovalRecord(String userId,long invoiceId,int status,int approvalStatus){
		InvoiceApproval invoiceApproval = new InvoiceApproval();
		invoiceApproval.setInvoiceId(invoiceId);
		invoiceApproval.setStatus(status);
		invoiceApproval.setUserId(userId);
		invoiceApproval.setApprovalStatus(approvalStatus);
		invoiceApproval.setUpdateTime(new Timestamp(new Date().getTime()));
		return invoiceApprovalDao.updateInvoiceApproval(invoiceApproval);
	}
	
	@Override
	public boolean addInvoiceAppovalRecord(InvoiceApproval invoiceApproval) {
		return invoiceApprovalDao.addInvoiceApproval(invoiceApproval);
	}
	
	@Override
	public boolean addInvoiceApprovalRecord(long invoiceId, String approvalId,
			int status) {
		InvoiceApproval invoiceApproval = new InvoiceApproval();
		invoiceApproval.setInvoiceId(invoiceId);
		//Set status to -1 first, set it back to 0 if the invoice is recognized
		invoiceApproval.setStatus(-1);
		invoiceApproval.setUserId(approvalId);
		Timestamp time = new Timestamp(new Date().getTime());
		invoiceApproval.setCreateTime(time);
		invoiceApproval.setUpdateTime(time);
		return invoiceApprovalDao.addInvoiceApproval(invoiceApproval);
	}


}
