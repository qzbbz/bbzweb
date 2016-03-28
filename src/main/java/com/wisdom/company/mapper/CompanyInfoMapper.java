package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanyInfo;

public class CompanyInfoMapper implements RowMapper<CompanyInfo> {
	public CompanyInfo mapRow(ResultSet rs, int index) throws SQLException {
		CompanyInfo u = new CompanyInfo(rs.getLong("id"), rs.getString("name"),
				rs.getString("perfect_moment"),rs.getString("month_expense"),rs.getString("create_time"),rs.getString("user_id"), rs.getInt("audit_status"), rs.getInt("type_id"),rs.getString("phone"));
		return u;
	}
}
