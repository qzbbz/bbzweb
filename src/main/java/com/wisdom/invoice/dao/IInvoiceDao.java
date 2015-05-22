package com.wisdom.invoice.dao;

import java.util.List;

import com.wisdom.common.model.Invoice;

public interface IInvoiceDao {

	public Invoice getInvoiceById(long id);
	public Invoice getInvoiceByIdAndStatus(long invoiceId,int status);
	public Invoice getInvoiceByCode(String invoiceCode);
	
	public boolean updateInvoiceIdentifyStatus(long invoiceId, int status);
	
	public long addInvoiceAndGetKey(Invoice invoice);
	
	public boolean deleteInvoice(Invoice invoice);
	
	public boolean deleteInvoiceByInvoiceId(long invoiceId);
	
	public boolean updateInvoice(Invoice invoice);
	public List<Invoice> getUserInvoiceByStatus(String userId,String status);
	public List<Invoice> getUserInvoiceByStatusByPage(String userId,String status,int begin,int end);
	public boolean updateInvoiceStatus(long invoiceId,int status);
}