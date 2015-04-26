package com.wisdom.accounter.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Accounter;

public class AccounterMapper implements RowMapper<Accounter> {
	public Accounter mapRow(ResultSet rs, int index) throws SQLException {
		Accounter u = new Accounter(rs.getLong("id"), rs.getString("user_id"),
				rs.getString("name"), rs.getLong("area_id"),
				rs.getString("image"), rs.getLong("certificate_id"),
				rs.getLong("industry_id"), rs.getLong("career_id"),
				rs.getTimestamp("create_time"));
		return u;
	}
}
