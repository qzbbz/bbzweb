package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.ExpenseType;

public class ExpenseTypeMapper  implements RowMapper<ExpenseType> {
	public ExpenseType mapRow(ResultSet rs, int index) throws SQLException {
		ExpenseType u = new ExpenseType(rs.getLong("id"), rs.getString("name"),
				rs.getTimestamp("create_time"), rs.getInt("hit"));
		return u;
	}
}