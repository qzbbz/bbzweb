package com.wisdom.sales.dao;

import java.util.List;

import com.wisdom.common.model.Sales;;

public interface ISalesDao {

	public List<Sales> getAllSalesRecords();
	
	public Sales getSalesById(Integer id);
	
	public long addSalesRecord(Sales sales);
	
	public Boolean updateSalesRecordStatus(String userPhone, String status);
	
	public List<Sales> getAllSalesRecordsByUpdatedTime(String time);
	
	public Boolean updateSalesRecordInformation(Integer id, String accountant, String updatedTime, String status, String accountantId);
	
	public Boolean updateSalesRecordLatestComment(Integer id, String comment);
	
	public Boolean updateSalesSendEmailStatus(Integer id, Integer status);
	
	public List<Sales> getSalesBySallerId(String sallerId);
	
	public List<Sales> getSalesByAccountantId(String accountantId);
	
}