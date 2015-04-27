package com.wisdom.community.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Community;

public class CommunityMapper implements RowMapper<Community> {
	public Community mapRow(ResultSet rs, int index) throws SQLException {
		Community u = new Community(rs.getLong("id"), rs.getString("user_id"),
				rs.getLong("tag_id"), rs.getString("title"),
				rs.getString("abstract_data"), rs.getString("data"),
				rs.getTimestamp("create_time"), rs.getTimestamp("update-Time"));
		return u;
	}
}