package com.wisdom.recommender.dao;

import java.util.List;

import com.wisdom.common.model.RecommendInfo;

public interface IRecommendInfoDao {


	
	public boolean addRecommendInfo(RecommendInfo recommendInfo);
	
	public List<RecommendInfo> getRecommendInfoByIP(String ip);

	
	
	
}