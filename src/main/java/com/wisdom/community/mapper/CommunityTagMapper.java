package com.wisdom.community.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CommunityTag;

public class CommunityTagMapper implements RowMapper<CommunityTag> {
	public CommunityTag mapRow(ResultSet rs, int index) throws SQLException {
		CommunityTag u = new CommunityTag(rs.getLong("id"),
				rs.getString("name"), rs.getTimestamp("create_time"));
		return u;
	}
}