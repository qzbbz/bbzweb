package com.wisdom.recommender.service;
import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Recommender;
import com.wisdom.common.model.RecommendRecord;

public interface IRecommendService {
	
	public Boolean addRecommender(Recommender recommender);
	
	public Map<String, String> getAllRecommender();
	
	public Boolean addRecommendRecord(RecommendRecord recommendRecord );
	
	public Map<String, List<String>> getAllRecommendRecord();
}
