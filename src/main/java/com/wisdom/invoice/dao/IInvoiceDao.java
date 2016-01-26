package com.wisdom.invoice.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Invoice;
import com.wisdom.common.model.TestInvoice;
import com.wisdom.common.model.TestInvoiceRecord;

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
	
	public boolean setIsFAOfInvoice(long invoiceId, boolean isFA, String itemId);
	
	public boolean addInvoiceArtifact(long invoiceId, double amount, String type, String supplierName, double tax, String itemId, Integer number);
	
	public boolean deleteInvoiceArtifactByInvoiceId(long invoiceId);
	
	public long addInvoice(TestInvoice invoice);
	
	public boolean removeRedundantInvoiceArtifact(Timestamp toTime);
	
	public List<TestInvoiceRecord> getAllInvoicesByCompanyId(long companyId);
	
	public boolean updateInoviceStatus(long invoiceId, String status);
	
	public List<TestInvoiceRecord> getInvoicesByDate(String date, long companyId);
	
	public boolean deleteTestInvoice(long invoiceId);
	
	public TestInvoiceRecord getInvoiceRecordById(long invoiceId);
	
	public List<TestInvoiceRecord> getAllCompanyInvoicesByCompanyId(long companyId);
	
	public List<TestInvoice> getUngeneratedInvoices(Integer limit);
	
	public boolean setInvoiceToGenerated(long invoiceId);
	
	public boolean setInvoiceToInvalid(long invoiceId);
	
	public boolean setInvoiceComment(long invoiceId, String comment);
}