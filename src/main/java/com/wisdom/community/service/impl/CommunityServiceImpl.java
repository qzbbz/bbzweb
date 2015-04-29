package com.wisdom.community.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Community;
import com.wisdom.common.model.CommunityTag;
import com.wisdom.community.dao.ICommentsDao;
import com.wisdom.community.dao.ICommunityDao;
import com.wisdom.community.dao.ICommunityTagDao;
import com.wisdom.community.service.ICommunityService;

@Service("communityService")
public class CommunityServiceImpl implements ICommunityService {

	@Autowired
	private ICommunityDao communityDao;
	
	@Autowired
	private ICommunityTagDao communityTagDao;
	
	@Autowired
	private ICommentsDao commentsDao;
	
	@Override
	public Map<Long, String> getAllCommunityTags() {
		Map<Long, String> retMap = new HashMap<>();
		List<CommunityTag> list = communityTagDao.getAllCommunityTag();
		if(list != null && list.size() > 0) {
			for(CommunityTag tag : list)
				retMap.put(tag.getId(), tag.getName());
		}
		return retMap;
	}

	@Override
	public List<Map<String, String>> getCommunityByTagId(long id) {
		List<Map<String, String>> retList = new ArrayList<>();
		List<Community> list = communityDao.getCommunityByTagId(id);
		if(list != null && list.size() > 0) {
			for(Community community : list) {
				retList.add(communityConvertToMap(community));
			}
		}
		return retList;
	}

	@Override
	public List<Map<String, String>> getCommunityByKeywords(String keywords) {
		List<Map<String, String>> retList = new ArrayList<>();
		List<Community> list = communityDao.getCommunityByKeywords(keywords);
		if(list != null && list.size() > 0) {
			for(Community community : list) {
				retList.add(communityConvertToMap(community));
			}
		}
		return retList;
	}

	@Override
	public List<Map<String, String>> getCommunityByOffset(long startOffset,
			long endOffset) {
		List<Map<String, String>> retList = new ArrayList<>();
		List<Community> list = communityDao.getCommunityByOffset(startOffset, endOffset);
		if(list != null && list.size() > 0) {
			for(Community community : list) {
				retList.add(communityConvertToMap(community));
			}
		}
		return retList;
	}

	@Override
	public boolean publishArticle(Community community) {
		return communityDao.addCommunity(community);
	}

	@Override
	public boolean modifyArticle(Community community) {
		return communityDao.updateCommunity(community);
	}
	
	private Map<String, String> communityConvertToMap(Community community) {
		Map<String, String> map = new HashMap<>();
		map.put("abstractData", community.getAbstractData());
		map.put("data", community.getData());
		map.put("title", community.getTitle());
		map.put("image", community.getImage());
		map.put("update_time", community.getUpdateTime().toString());
		map.put("tag_id", String.valueOf(community.getTagId()));
		map.put("id", String.valueOf(community.getId()));
		return map;
	}

}
