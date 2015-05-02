package com.wisdom.community.service;

import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Community;

public interface ICommunityService {

	public Map<Long, String> getAllCommunityTags();
	
	public List<Map<String, String>> getCommunityByTagId(long id);
	
	public List<Map<String, String>> getCommunityByKeywords(String keywords);
	
	public List<Map<String, String>> getCommunityByOffset(long startOffset, long endOffset);
	
	public boolean publishArticle(Community community);
	
	public boolean modifyArticle(Community community);
	
}
