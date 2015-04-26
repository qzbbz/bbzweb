package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Role;

public class RoleMapper  implements RowMapper<Role> {
	public Role mapRow(ResultSet rs, int index) throws SQLException {
		Role u = new Role(rs.getLong("id"), rs.getString("name"),
				rs.getTimestamp("create_time"));
		return u;
	}
}