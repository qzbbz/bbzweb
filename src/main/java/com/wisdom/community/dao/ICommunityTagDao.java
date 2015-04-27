package com.wisdom.community.dao;

import com.wisdom.common.model.CommunityTag;

public interface ICommunityTagDao {

	public CommunityTag getCommunityTagById(long id);
	
	public boolean addCommunityTag(CommunityTag communityTag);
	
	public boolean deleteCommunityTag(CommunityTag communityTag);
	
	public boolean updateCommunityTag(CommunityTag communityTag);
	
}