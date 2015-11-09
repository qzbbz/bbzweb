package com.wisdom.recommender.dao;

import java.util.List;

import com.wisdom.common.model.RecommendRecord;

public interface IRecommendRecordDao {

	public RecommendRecord getRecommendRecordById(String recommender_id);
	
	public List<RecommendRecord> getAllRecommendRecord();
	
	public boolean addRecommendRecord(RecommendRecord recommendRecord);
	
	public boolean deleteRecommendRecord(RecommendRecord recommendRecord);
	
	public boolean updateRecommendRecord(RecommendRecord recommendRecord);
	
	public RecommendRecord getRecommendRecordByEmail(String customer_email);
	
	
}
