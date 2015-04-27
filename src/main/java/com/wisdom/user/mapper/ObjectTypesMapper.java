package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.ObjectTypes;

public class ObjectTypesMapper  implements RowMapper<ObjectTypes> {
	public ObjectTypes mapRow(ResultSet rs, int index) throws SQLException {
		ObjectTypes u = new ObjectTypes(rs.getLong("id"), rs.getString("name"),
				rs.getTimestamp("create_time"));
		return u;
	}
}