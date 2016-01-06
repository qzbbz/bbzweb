package com.wisdom.recommender.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.RecommendInfo;

public class RecommendInfoMapper implements RowMapper<RecommendInfo> {
	public RecommendInfo mapRow(ResultSet rs, int index) throws SQLException {
		RecommendInfo u = new RecommendInfo(rs.getString("recommender_id"),
				rs.getString("ip"), rs.getTimestamp("created_time"));
		return u;
	}
}