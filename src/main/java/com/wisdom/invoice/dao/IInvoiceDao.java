package com.wisdom.invoice.dao;

import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Invoice;
import com.wisdom.common.model.TestInvoice;

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
	
	public boolean setIsFAOfInvoice(long invoiceId, boolean isFA);
	
	public boolean addInvoiceArtifact(long invoiceId, double amount, String type, String supplierName);
	
	public boolean deleteInvoiceArtifactByInvoiceId(long invoiceId);
	
	public long addInvoice(TestInvoice invoice);
}