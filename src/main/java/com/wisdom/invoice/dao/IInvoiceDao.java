package com.wisdom.invoice.dao;

import com.wisdom.common.model.Invoice;

public interface IInvoiceDao {

	public Invoice getInvoiceById(long id);
	public Invoice getInvoiceByIdAndStatus(long invoiceId,int status);
	
	public long addInvoiceAndGetKey(Invoice invoice);
	
	public boolean deleteInvoice(Invoice invoice);
	
	public boolean updateInvoice(Invoice invoice);
	
}