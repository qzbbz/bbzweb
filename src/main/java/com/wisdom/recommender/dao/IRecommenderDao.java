package com.wisdom.recommender.dao;

import java.util.List;

import com.wisdom.common.model.Recommender;

public interface IRecommenderDao {

	public Recommender getRecommenderById(String id);
	
	public List<Recommender> getAllRecommender();
	
	public boolean addRecommender(Recommender recommender);
	
	public boolean deleteRecommender(Recommender recommender);
	
	public boolean updateRecommender(Recommender recommender);
	
}
