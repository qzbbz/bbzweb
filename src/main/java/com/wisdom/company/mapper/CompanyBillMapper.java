package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanyPay;

public class CompanyBillMapper implements RowMapper<CompanyBill> {
	public CompanyBill mapRow(ResultSet rs, int index) throws SQLException {
		CompanyBill u = new CompanyBill(rs.getLong("id"), rs.getLong("company_id"),
				rs.getDouble("amount"), rs.getString("type"), rs.getString("file_name"), 
				rs.getString("bill_date"), rs.getString("supply_name"), 
				rs.getInt("is_fixed_assets"), rs.getTimestamp("create_time"));
		return u;
	}
}
