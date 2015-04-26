package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.RolePrivate;

public class RolePrivateMapper  implements RowMapper<RolePrivate> {
	public RolePrivate mapRow(ResultSet rs, int index) throws SQLException {
		RolePrivate u = new RolePrivate(rs.getLong("id"), rs.getString("name"),
				rs.getLong("company_id"), rs.getTimestamp("create_time"));
		return u;
	}
}
