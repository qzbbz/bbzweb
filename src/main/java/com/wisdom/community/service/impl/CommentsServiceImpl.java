package com.wisdom.community.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Comments;
import com.wisdom.community.dao.ICommentsDao;
import com.wisdom.community.service.ICommentsService;

@Service("commentsService")
public class CommentsServiceImpl implements ICommentsService {

	@Autowired
	private ICommentsDao commentsDao;
	
	@Override
	public List<Map<String, String>> getCommentsByCommunityId(long id) {
		List<Map<String, String>> retList = new ArrayList<>();
		List<Comments> list = commentsDao.getCommentsByCommunityId(id);
		if(list != null && list.size() > 0) {
			for(Comments comm : list)
				retList.add(commentConvertToMap(comm));
		}
		return retList;
	}

	@Override
	public List<Map<String, String>> getCommnetsByUserId(String userId) {
		List<Map<String, String>> retList = new ArrayList<>();
		List<Comments> list = commentsDao.getCommentsByUserId(userId);
		if(list != null && list.size() > 0) {
			for(Comments comm : list)
				retList.add(commentConvertToMap(comm));
		}
		return retList;
	}

	@Override
	public boolean addComments(Comments comment) {
		return commentsDao.addComments(comment);
	}

	@Override
	public boolean updateComments(Comments comment) {
		return commentsDao.updateComments(comment);
	}

	@Override
	public boolean deleteComments(Comments comment) {
		return commentsDao.deleteComments(comment);
	}

	private Map<String, String> commentConvertToMap(Comments comment) {
		Map<String, String> map = new HashMap<>();
		map.put("communityId", String.valueOf(comment.getCommunityId()));
		map.put("comment", comment.getComment());
		map.put("user_id", comment.getUserId());
		map.put("update_time", comment.getCreateTime().toString());
		return map;
	}
	
}
