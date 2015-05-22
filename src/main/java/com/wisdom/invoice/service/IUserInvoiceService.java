package com.wisdom.invoice.service;


import java.util.List;

import org.springframework.util.StringUtils;

import com.wisdom.common.model.InvoiceInfo;
import com.wisdom.common.model.UserInvoice;

public interface IUserInvoiceService {
	
	public UserInvoice getUserInvoiceByInvoiceId(long longId);
	public UserInvoice getUserInvoiceByUserIdAndInvoiceId(String userId,long invoiceId);
	public List<UserInvoice> getUserInvoiceByUserId(String userId);
	public boolean addUserInvoiceRecord(long invoiceId,String userId,String receiver,int status);
	public boolean updateInvoiceApprovalStatus(String userId,String approvalUserId,long invoiceId,int status,int approvalStatus,String reasons);
	
	public List<InvoiceInfo> getInvoiceInfoByCondition(String userId,
			String date, String submitter, Double amount, Integer expenseType,Integer page,Integer pageSize);
}
