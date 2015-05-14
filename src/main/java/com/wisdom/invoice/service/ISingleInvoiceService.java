package com.wisdom.invoice.service;

import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Invoice;

public interface ISingleInvoiceService {
	
	public boolean updateInvoiceInfo(long invoiceId,String desc,String title,double amount ,long expenseType,
			int status,String date);
	
	public List<Invoice> getUserInvoiceByStatus(String userId,String status);
	
	public List<Invoice> getUserInvoiceByStatusByPage(String userId,String status,int page,int pageSize);
	
	public boolean updateInvoiceStatus(long invoiceId,int status);
	
	public boolean updateInvoiceInfo(Map<String, String> params);
}
