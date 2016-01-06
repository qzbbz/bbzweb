package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanyPayHistory;

public class CompanyPayHistoryMapper implements RowMapper<CompanyPayHistory> {
	public CompanyPayHistory mapRow(ResultSet rs, int index) throws SQLException {
		CompanyPayHistory u = new CompanyPayHistory(rs.getLong("id"), rs.getLong("company_id"),
				rs.getDouble("pay_amount"), rs.getInt("service_time"), 
				rs.getString("order_no"), rs.getInt("apply_invoice"), 
				rs.getString("mail_address"), rs.getString("contract_file"), rs.getTimestamp("created_time"), rs.getInt("pay_status"));
		return u;
	}
}
