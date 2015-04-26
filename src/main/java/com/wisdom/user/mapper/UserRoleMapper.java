package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.UserRole;

public class UserRoleMapper  implements RowMapper<UserRole> {
	public UserRole mapRow(ResultSet rs, int index) throws SQLException {
		UserRole u = new UserRole(rs.getLong("id"), rs.getLong("user_id"),
				rs.getLong("role_id"), rs.getTimestamp("create_time"));
		return u;
	}
}