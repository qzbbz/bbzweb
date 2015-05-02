package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.UserInviteCode;

public class UserInviteCodeMapper implements RowMapper<UserInviteCode> {
	public UserInviteCode mapRow(ResultSet rs, int index) throws SQLException {
		UserInviteCode u = new UserInviteCode(rs.getLong("id"), rs.getString("user_id"),
				rs.getString("invite_code"), rs.getTimestamp("create_time"));
		return u;
	}
}
