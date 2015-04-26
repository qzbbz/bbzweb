package com.wisdom.accounter.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.AccounterIndustry;

public class AccounterIndustryMapper implements RowMapper<AccounterIndustry> {
	public AccounterIndustry mapRow(ResultSet rs, int index) throws SQLException {
		AccounterIndustry u = new AccounterIndustry(rs.getLong("id"),
				rs.getString("name"), rs.getTimestamp("create_time"));
		return u;
	}
}
