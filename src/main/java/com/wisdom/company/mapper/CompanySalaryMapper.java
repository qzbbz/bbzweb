package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanySalary;

public class CompanySalaryMapper implements RowMapper<CompanySalary> {
	public CompanySalary mapRow(ResultSet rs, int index) throws SQLException {
		CompanySalary u = new CompanySalary(rs.getLong("id"), rs.getLong("company_id"),
				rs.getString("salary_file"), rs.getString("salary_date"), rs.getTimestamp("create_time"));
		return u;
	}
}
