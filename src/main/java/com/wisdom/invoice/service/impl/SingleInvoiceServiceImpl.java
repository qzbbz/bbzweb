package com.wisdom.invoice.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Invoice;
import com.wisdom.invoice.dao.IInvoiceDao;
import com.wisdom.invoice.service.ISingleInvoiceService;

@Service("singleInvoiceService")
public class SingleInvoiceServiceImpl implements ISingleInvoiceService {

	private static final Log log = LogFactory.getLog(SingleInvoiceServiceImpl.class);
	
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
	@Override
	public List<Invoice> getUserInvoiceByStatus(String userId,String status){
		return invoiceDao.getUserInvoiceByStatus(userId, status);
	}
	
	@Override
	public List<Invoice> getUserInvoiceByStatusByPage(String userId,String status,int page,int pageSize){
		int start = (page-1)*pageSize;
		int end = page*pageSize;
		return invoiceDao.getUserInvoiceByStatusByPage(userId, status, start, end);
	}
	@Override
	public boolean updateInvoiceStatus(long invoiceId,int status){
		return invoiceDao.updateInvoiceStatus(invoiceId,status);
	}
	@Override
	public boolean updateInvoiceInfo(Map<String, String> params) {
		if(params == null || !params.containsKey("invoiceId")) {
			log.warn("params is null or invoice id is null.");
			return false;
		}
		Invoice invoice = invoiceDao.getInvoiceById(Long.valueOf(params.get("invoiceId")));
		if(invoice == null) {
			log.error("invoice id is not found, id : " + params.get("invoiceId"));
			return false;
		}
		boolean shouldUpdate = false;
		if(params.containsKey("amount") && !params.get("amount").isEmpty()) {
			invoice.setAmount(Double.valueOf(params.get("amount")));
			shouldUpdate = true;
		}
		if(params.containsKey("expenseTypeId") && !params.get("expenseTypeId").isEmpty()) {
			invoice.setExpenseTypeId(Integer.valueOf(params.get("expenseTypeId")));
			shouldUpdate = true;
		}
		if(params.containsKey("detailDesc") && !params.get("detailDesc").isEmpty()) {
			invoice.setDesc(params.get("detailDesc"));
			shouldUpdate = true;
		}
		if(shouldUpdate) {
			return invoiceDao.updateInvoice(invoice);
		}
		log.debug("no info need to update!");
		return false;
	}
	
}
