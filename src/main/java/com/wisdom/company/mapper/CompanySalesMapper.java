package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanySales;

public class CompanySalesMapper implements RowMapper<CompanySales> {
	public CompanySales mapRow(ResultSet rs, int index) throws SQLException {
		CompanySales u = new CompanySales(rs.getLong("id"), rs.getLong("company_id"),
				rs.getDouble("amount"), rs.getString("type"), rs.getString("file_name"), 
				rs.getTimestamp("create_time"));
		return u;
	}
}
