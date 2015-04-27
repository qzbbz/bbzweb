package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.UserType;

public class UserTypeMapper  implements RowMapper<UserType> {
	public UserType mapRow(ResultSet rs, int index) throws SQLException {
		UserType u = new UserType(rs.getInt("id"), rs.getString("name"),
				rs.getTimestamp("create_time"));
		return u;
	}
}