package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.UserDept;

public class UserDeptMapper  implements RowMapper<UserDept> {
	public UserDept mapRow(ResultSet rs, int index) throws SQLException {
		UserDept u = new UserDept(rs.getLong("id"), rs.getString("user_id"),
				rs.getLong("dept_id"), rs.getInt("status"), rs.getTimestamp("create_time"));
		return u;
	}
}