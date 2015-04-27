package com.wisdom.dispatch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.ChannelType;

public class ChannelTypeMapper implements RowMapper<ChannelType> {
	public ChannelType mapRow(ResultSet rs, int index) throws SQLException {
		ChannelType u = new ChannelType(rs.getLong("id"), rs.getString("name"),
				rs.getTimestamp("create_time"));
		return u;
	}
}
