package com.wisdom.sales.service;

import java.util.List;

import com.wisdom.common.model.Sales;
import com.wisdom.common.model.SalesComment;

public interface ISalesService {

	Integer addSalesRecord(Sales sales);
	
	List<Sales> getSalesRecords();
	
	Sales getSalesRecordById(Integer id);
	
	Boolean updateSales(Integer id, String comment, String accountant, String updatedTime, String status);
	
	List<Sales> getSalesRecordsByUpdatedTime(String time);
	
	List<SalesComment> getSalesCommentsBySaleId(Integer saleId);
	
	Boolean updateSalesStatus(String userPhone, String status);
	
	Boolean updateSalesSendEmailStatus(int id, int status);
}
