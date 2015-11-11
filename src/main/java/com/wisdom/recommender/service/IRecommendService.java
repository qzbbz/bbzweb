package com.wisdom.recommender.service;
import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Recommender;
import com.wisdom.common.model.RecommendRecord;

public interface IRecommendService {
	
	public Boolean addRecommender(Recommender recommender);
	
	public Map<String, List<String>> getAllRecommender();
	
	public Boolean addRecommendRecord(RecommendRecord recommendRecord );
	
	public Map<String, List<String>> getAllRecommendRecord();
	
	public Boolean isRecommenderExisted(String name);
	
	public Boolean isRecommendRecordExisted(String email);
	
	public Boolean isCustomerPaid(String email);
	
	public Boolean setCustomerPaid(String email);
	
	public String getCustomerEmailByCompanyId(long id);
	
	public Boolean addRecommendInfo(String code, String ip);
}
