package com.wisdom.invoice.dao;

import java.util.List;

import com.wisdom.common.model.InvoiceApproval;
import com.wisdom.invoice.domain.InvoiceInfoVo;

public interface IInvoiceApprovalDao {

	public InvoiceApproval getInvoiceApprovalByInvoiceId(long id);
	
	public boolean addInvoiceApproval(InvoiceApproval invoiceApproval);
	
	public boolean deleteInvoiceApproval(InvoiceApproval invoiceApproval);
	
	public boolean updateInvoiceApproval(InvoiceApproval invoiceApproval);
	
	public List<InvoiceApproval> getInvoiceApprovalListByUserId(String userId);
	
	
}
