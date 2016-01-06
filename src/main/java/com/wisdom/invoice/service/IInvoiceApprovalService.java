package com.wisdom.invoice.service;

import java.util.List;

import com.wisdom.common.model.InvoiceApproval;

public interface IInvoiceApprovalService {
	public List<InvoiceApproval> getInvoiceApprovalListByUserId(String userId);
	public boolean updateApprovalRecord(String userId,long invoiceId,int status,int approvalStatus);
	public boolean addInvoiceAppovalRecord(InvoiceApproval invoiceApproval);
	public boolean addInvoiceApprovalRecord(long invoiceId, String approvalId,
			int status) ;
	public List<InvoiceApproval> getInvoiceApprovalListByInvoiceIds(String invoiceIds);
}
