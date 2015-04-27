package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Dept;

public class DeptMapper implements RowMapper<Dept> {
	public Dept mapRow(ResultSet rs, int index) throws SQLException {
		Dept u = new Dept(rs.getLong("id"), rs.getString("name"),
				rs.getLong("company_id"), rs.getLong("parent_id"), 
				rs.getTimestamp("create_time"));
		return u;
	}
}
