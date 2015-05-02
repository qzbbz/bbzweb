package com.wisdom.accounter.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Accounter;

public class AccounterMapper implements RowMapper<Accounter> {
	public Accounter mapRow(ResultSet rs, int index) throws SQLException {
		Accounter u = new Accounter(rs.getLong("id"), rs.getString("user_id"),
				rs.getString("area"), rs.getString("city"),
				rs.getString("province"), rs.getString("image"),
				rs.getString("certificate"), rs.getString("industry"),
				rs.getString("career"), rs.getTimestamp("create_time"));
		return u;
	}
}
