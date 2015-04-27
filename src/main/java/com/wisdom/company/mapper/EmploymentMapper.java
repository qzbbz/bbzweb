package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Employment;

public class EmploymentMapper implements RowMapper<Employment> {
	public Employment mapRow(ResultSet rs, int index) throws SQLException {
		Employment u = new Employment(rs.getLong("id"),
				rs.getLong("company_id"), rs.getString("user_id"),
				rs.getTimestamp("create_time"));
		return u;
	}
}