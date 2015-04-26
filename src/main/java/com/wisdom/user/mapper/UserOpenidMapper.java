package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.wisdom.common.model.UserOpenid;

public class UserOpenidMapper implements RowMapper<UserOpenid> {

	public UserOpenid mapRow(ResultSet rs, int index) throws SQLException {
		UserOpenid u = new UserOpenid(rs.getLong("id"), rs.getString("user_id"),
				rs.getString("openid"), rs.getTimestamp("create_time"));
		return u;
	}

}
