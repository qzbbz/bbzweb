package com.wisdom.invoice.service;

public interface ISingleInvoiceService {
	
	public boolean updateInvoiceInfo(long invoiceId,String desc,String title,double amount ,long expenseType,
			int status,String date);
}
