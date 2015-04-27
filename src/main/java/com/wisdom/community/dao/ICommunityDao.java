package com.wisdom.community.dao;

import java.util.List;

import com.wisdom.common.model.Community;

public interface ICommunityDao {
	
	public List<Community> getCommunityByTagId(long tagId);
	
	public List<Community> getCommunityByKeywords(String keywords);
	
	public List<Community> getCommunityByOffset(long startOffset, long endOffset);
	
	public boolean addCommunity(Community community);
	
	public boolean deleteCommunity(Community community);
	
	public boolean updateCommunity(Community community);
	
}