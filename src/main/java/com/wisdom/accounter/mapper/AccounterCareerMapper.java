package com.wisdom.accounter.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.AccounterCareer;

public class AccounterCareerMapper implements RowMapper<AccounterCareer> {
	public AccounterCareer mapRow(ResultSet rs, int index) throws SQLException {
		AccounterCareer u = new AccounterCareer(rs.getLong("id"), rs.getString("name"),
				rs.getTimestamp("create_time"));
		return u;
	}
}
