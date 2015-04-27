package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Menu;

public class MenuMapper   implements RowMapper<Menu> {
	public Menu mapRow(ResultSet rs, int index) throws SQLException {
		Menu u = new Menu(rs.getLong("id"), rs.getString("name"),
				rs.getString("url"), rs.getTimestamp("create_time"));
		return u;
	}
}