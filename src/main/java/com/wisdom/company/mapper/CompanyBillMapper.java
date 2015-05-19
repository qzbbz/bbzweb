package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanyBill;

public class CompanyBillMapper implements RowMapper<CompanyBill> {
	public CompanyBill mapRow(ResultSet rs, int index) throws SQLException {
		CompanyBill u = new CompanyBill(rs.getLong("id"), rs.getLong("company_id"),
				rs.getString("file_name"), rs.getTimestamp("create_time"));
		return u;
	}
}
