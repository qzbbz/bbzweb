package com.wisdom.sales.dao;

import java.util.List;

import com.wisdom.common.model.SalesComment;



public interface ISalesCommentDao {

	public List<SalesComment> getAllSalesCommentsBySalesId(Integer salesId);
	
	public Boolean addSalesComment(SalesComment comment);
	

	
	
	
}