package com.wisdom.community.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Comments;
import com.wisdom.community.dao.ICommentsDao;
import com.wisdom.community.mapper.CommentsMapper;

@Repository("commentsDao")
public class CommentsDaoImpl implements ICommentsDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CommentsDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Comments> getCommentsByCommunityId(long communityId) {
		List<Comments> list = null;
		try {
			String sql = "select * from comments where id =?";
			list = jdbcTemplate.query(sql, new Object[]{communityId},
					new RowMapperResultSetExtractor<Comments>(
							new CommentsMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<Comments> getCommentsByUserId(String userId) {
		List<Comments> list = null;
		try {
			String sql = "select * from comments where user_id=?";
			list = jdbcTemplate.query(sql, new Object[]{userId},
					new RowMapperResultSetExtractor<Comments>(
							new CommentsMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean addComments(Comments comments) {
		String sql = "insert into comments (community_id, comment, user_id, create_time)"
				+ " values (?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, comments.getCommunityId(),
				comments.getComment(), comments.getUserId(), comments.getCreateTime());
		logger.debug("addComments result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteComments(Comments comments) {
		String sql = "delete from comments where id = ?";
		int affectedRows = jdbcTemplate.update(sql, comments.getId());
		logger.debug("deleteComments result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateComments(Comments comments) {
		String sql = "update comments set comment=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, comments.getComment(),
				comments.getId());
		logger.debug("updateComments result : {}", affectedRows);
		return affectedRows != 0;
	}

}
