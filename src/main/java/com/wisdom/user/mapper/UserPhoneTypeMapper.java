package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.UserPhoneType;

public class UserPhoneTypeMapper  implements RowMapper<UserPhoneType> {
	public UserPhoneType mapRow(ResultSet rs, int index) throws SQLException {
		UserPhoneType u = new UserPhoneType(rs.getLong("id"), rs.getString("name"),
				rs.getTimestamp("create_time"));
		return u;
	}
}