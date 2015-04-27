package com.wisdom.invoice.dao;

import com.wisdom.common.model.InvoiceApproval;

public interface IInvoiceApprovalDao {

	public InvoiceApproval getInvoiceApprovalByInvoiceId(long id);
	
	public boolean addInvoiceApproval(InvoiceApproval invoiceApproval);
	
	public boolean deleteInvoiceApproval(InvoiceApproval invoiceApproval);
	
	public boolean updateInvoiceApproval(InvoiceApproval invoiceApproval);
	
}
