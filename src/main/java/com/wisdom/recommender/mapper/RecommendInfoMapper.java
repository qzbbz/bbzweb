package com.wisdom.recommender.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Recommender;

public class RecommendInfoMapper implements RowMapper<Recommender> {
	public Recommender mapRow(ResultSet rs, int index) throws SQLException {
		Recommender u = new Recommender(rs.getString("recommender_id"),
				rs.getString("ip"));
		return u;
	}
}