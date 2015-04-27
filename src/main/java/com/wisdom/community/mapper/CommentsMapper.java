package com.wisdom.community.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Comments;

public class CommentsMapper implements RowMapper<Comments> {
	public Comments mapRow(ResultSet rs, int index) throws SQLException {
		Comments u = new Comments(rs.getLong("id"), rs.getLong("community_id"),
				rs.getString("comment"), rs.getString("user_id"),
				rs.getTimestamp("create_time"));
		return u;
	}
}
