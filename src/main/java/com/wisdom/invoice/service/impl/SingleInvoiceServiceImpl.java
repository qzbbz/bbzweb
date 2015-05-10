package com.wisdom.invoice.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Invoice;
import com.wisdom.invoice.dao.IInvoiceDao;
import com.wisdom.invoice.service.ISingleInvoiceService;

@Service("singleInvoiceService")
public class SingleInvoiceServiceImpl implements ISingleInvoiceService {

	@Autowired
	private IInvoiceDao invoiceDao;
	
	@Override
	public boolean updateInvoiceInfo(long invoiceId,String desc,String title,double amount ,long expenseType,
				int status,String date){
		Invoice invoice = new Invoice();
		invoice.setAmount(amount);
		invoice.setTitle(title);
		invoice.setDesc(desc);
		invoice.setDate(Timestamp.valueOf(date));
		invoice.setExpenseTypeId((int)expenseType);
		invoice.setId(invoiceId);
		
		return invoiceDao.updateInvoice(invoice);
	}
	
}
