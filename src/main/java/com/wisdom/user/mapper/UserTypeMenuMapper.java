package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.UserTypeMenu;

public class UserTypeMenuMapper   implements RowMapper<UserTypeMenu> {
	public UserTypeMenu mapRow(ResultSet rs, int index) throws SQLException {
		UserTypeMenu u = new UserTypeMenu(rs.getLong("id"), rs.getLong("user_type_id"),
				rs.getLong("menu_id"), rs.getTimestamp("create_time"));
		return u;
	}
}