package com.wisdom.recommender.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.RecommendRecord;

public class RecommendRecordMapper implements RowMapper<RecommendRecord> {
	public RecommendRecord mapRow(ResultSet rs, int index) throws SQLException {
		RecommendRecord u = new RecommendRecord(rs.getString("recommender_id"),
				rs.getString("customer_email"), rs.getTimestamp("created_time"));
		return u;
	}
}