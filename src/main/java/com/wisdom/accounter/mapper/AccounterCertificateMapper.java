package com.wisdom.accounter.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.AccounterCertificate;

public class AccounterCertificateMapper implements RowMapper<AccounterCertificate> {
	public AccounterCertificate mapRow(ResultSet rs, int index) throws SQLException {
		AccounterCertificate u = new AccounterCertificate(rs.getLong("id"),
				rs.getString("name"), rs.getTimestamp("create_time"));
		return u;
	}
}
