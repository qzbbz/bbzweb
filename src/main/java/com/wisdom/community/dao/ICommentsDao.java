package com.wisdom.community.dao;

import java.util.List;

import com.wisdom.common.model.Comments;

public interface ICommentsDao {

	public List<Comments> getCommentsByCommunityId(long communityId);
	
	public List<Comments> getCommentsByUserId(String userId);
	
	public boolean addComments(Comments comments);
	
	public boolean deleteComments(Comments comments);
	
	public boolean updateComments(Comments comments);
	
}
