package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.wisdom.common.model.RolePermission;

public class RolePermissionMapper  implements RowMapper<RolePermission> {
	public RolePermission mapRow(ResultSet rs, int index) throws SQLException {
		RolePermission u = new RolePermission(rs.getLong("id"), rs.getInt("object_type_id"),
				rs.getInt("auth_action_id"), rs.getTimestamp("create_time"));
		return u;
	}
}