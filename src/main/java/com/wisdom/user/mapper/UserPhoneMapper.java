package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.UserPhone;

public class UserPhoneMapper implements RowMapper<UserPhone> {
	
	public UserPhone mapRow(ResultSet rs, int index) throws SQLException {
		UserPhone u = new UserPhone(rs.getLong("id"), rs.getString("user_id"),
				rs.getString("phone"), rs.getInt("type"),
				rs.getTimestamp("create_time"));
		return u;
	}

}