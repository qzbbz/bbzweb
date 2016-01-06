package com.wisdom.recommender.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Recommender;

public class RecommenderRecordMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, List<String>> row = null;
		//Customer customer = new Customer();
		List<String> list = null;
		list.add(rs.getString("name"));
		list.add(rs.getString("ip"));
		row.put(rs.getString("recommender_id"), list);

		return row;
	}
}
