package com.wisdom.sales.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Sales;
import com.wisdom.common.model.SalesComment;
import com.wisdom.recommender.dao.IRecommenderDao;
import com.wisdom.recommender.service.IRecommendService;
import com.wisdom.sales.dao.ISalesCommentDao;
import com.wisdom.sales.dao.ISalesDao;
import com.wisdom.sales.service.ISalesService;

@Service("salesService")
public class SalesServiceImpl implements ISalesService {

	@Autowired
	private ISalesDao salesDao;
	
	
	@Autowired
	private ISalesCommentDao salesCommentDao;

	@Override
	public Integer addSalesRecord(Sales sales) {
		// TODO Auto-generated method stub
		Integer salesId = (int) salesDao.addSalesRecord(sales);
		SalesComment saleComment = new SalesComment();
		saleComment.setSalesId(salesId);
		saleComment.setComment(sales.getLatestComment());
		salesCommentDao.addSalesComment(saleComment);
		return salesId;
	}

	@Override
	public List<Sales> getSalesRecords() {

		return salesDao.getAllSalesRecords();
	}
	

	@Override
	public Boolean updateSales(Integer id, String comment, String accountant, String updatedTime, String status, String accountantId) {
		if(comment.equals("") || comment.isEmpty()){
			return salesDao.updateSalesRecordInformation(id, accountant, updatedTime, status, accountantId);
		}else{
			SalesComment saleComment = new SalesComment();
			saleComment.setSalesId(id);
			saleComment.setComment(comment);
			salesCommentDao.addSalesComment(saleComment);
			salesDao.updateSalesRecordLatestComment(id, comment);
			return salesDao.updateSalesRecordInformation(id, accountant, updatedTime, status, accountantId);
		}

	}

	@Override
	public List<Sales> getSalesRecordsByUpdatedTime(String time) {
		// TODO Auto-generated method stub
		return salesDao.getAllSalesRecordsByUpdatedTime(time);
	}

	@Override
	public List<SalesComment> getSalesCommentsBySaleId(Integer saleId) {
		// TODO Auto-generated method stub
		return salesCommentDao.getAllSalesCommentsBySalesId(saleId);
	}

	@Override
	public Boolean updateSalesStatus(String userPhone, String status) {
		// TODO Auto-generated method stub
		return salesDao.updateSalesRecordStatus(userPhone, status);
	}

	@Override
	public Boolean updateSalesSendEmailStatus(int id, int status) {
		// TODO Auto-generated method stub
		return salesDao.updateSalesSendEmailStatus(id, status);
	}

	@Override
	public Sales getSalesRecordById(Integer id) {
		// TODO Auto-generated method stub
		return salesDao.getSalesById(id);
	}

	@Override
	public List<Sales> getSalesRecordByUserId(String userId) {

		return salesDao.getUserRelatedSalesRecords(userId);
	}

	@Override
	public Boolean deleteSalesRecordById(Integer id) {
		// TODO Auto-generated method stub
		return salesDao.deleteRecord(id);
	}

}
