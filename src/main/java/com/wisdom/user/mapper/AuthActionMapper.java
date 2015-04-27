package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.AuthAction;

public class AuthActionMapper implements RowMapper<AuthAction> {
	public AuthAction mapRow(ResultSet rs, int index) throws SQLException {
		AuthAction u = new AuthAction(rs.getLong("id"), rs.getString("name"),
				rs.getTimestamp("create_time"));
		return u;
	}
}