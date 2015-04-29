package com.wisdom.community.service;

import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Comments;

public interface ICommentsService {

	public List<Map<String, String>> getCommentsByCommunityId(long id);
	
	public List<Map<String, String>> getCommnetsByUserId(String userId);
	
	public boolean addComments(Comments comment);
	
	public boolean updateComments(Comments comment);
	
	public boolean deleteComments(Comments comment);
	
}
