package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.AmountLimit;

public class AmountLimitMapper   implements RowMapper<AmountLimit> {
	public AmountLimit mapRow(ResultSet rs, int index) throws SQLException {
		AmountLimit u = new AmountLimit(rs.getLong("id"), rs.getLong("company_id"),
				rs.getLong("dept_id"), rs.getLong("role_id"), rs.getLong("limit_id"),
				rs.getTimestamp("create_time"));
		return u;
	}
}