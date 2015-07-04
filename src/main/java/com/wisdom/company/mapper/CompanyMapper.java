package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Company;

public class CompanyMapper implements RowMapper<Company> {
	public Company mapRow(ResultSet rs, int index) throws SQLException {
		Company u = new Company(rs.getLong("id"), rs.getString("name"),
				rs.getLong("parent_id"), rs.getInt("month_expense"), 
				rs.getString("perfect_moment"), rs.getString("take_type"),
				rs.getString("accounter_id"), rs.getTimestamp("create_time"));
		return u;
	}
}
