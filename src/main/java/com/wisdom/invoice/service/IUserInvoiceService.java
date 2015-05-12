package com.wisdom.invoice.service;


import java.util.List;

import com.wisdom.common.model.UserInvoice;

public interface IUserInvoiceService {
	
	public UserInvoice getUserInvoiceByInvoiceId(long longId);
	public List<UserInvoice> getUserInvoiceByUserId(String userId);
	public boolean addUserInvoiceRecord(long invoiceId,String userId,String receiver,int status);
	public boolean updateInvoiceApprovalStatus(String userId,String approvalUserId,long invoiceId,int status,int approvalStatus);
}
