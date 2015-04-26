package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.UserInvitecode;

public class UserInviteCodeMapper implements RowMapper<UserInvitecode> {
	public UserInvitecode mapRow(ResultSet rs, int index) throws SQLException {
		UserInvitecode u = new UserInvitecode(rs.getLong("id"), rs.getString("user_id"),
				rs.getString("invite_code"), rs.getTimestamp("create_time"));
		return u;
	}
}
