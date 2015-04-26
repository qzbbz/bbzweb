package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.UserPwd;

public class UserPwdMapper implements RowMapper<UserPwd> {

	public UserPwd mapRow(ResultSet rs, int index) throws SQLException {
		UserPwd u = new UserPwd(rs.getLong("id"), rs.getString("user_id"),
				rs.getString("pwd"), rs.getTimestamp("create_time"));
		return u;
	}
	
}
