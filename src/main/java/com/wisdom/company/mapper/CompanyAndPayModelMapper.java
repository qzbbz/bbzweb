package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanyAndPayModel;

public class CompanyAndPayModelMapper implements RowMapper<CompanyAndPayModel> {
	public CompanyAndPayModel mapRow(ResultSet rs, int index) throws SQLException {
		CompanyAndPayModel u = new CompanyAndPayModel(rs.getLong("id"), rs.getString("name"),
				rs.getInt("service_time"), rs.getDouble("pay_amount"), rs.getTimestamp("create_time"));
		return u;
	}
}
