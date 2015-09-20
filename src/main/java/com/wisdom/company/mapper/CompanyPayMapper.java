package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanyPay;

public class CompanyPayMapper implements RowMapper<CompanyPay> {
	public CompanyPay mapRow(ResultSet rs, int index) throws SQLException {
		CompanyPay u = new CompanyPay(rs.getLong("id"), rs.getLong("company_id"),
				rs.getInt("pay_status"), rs.getDouble("pay_amount"), rs.getInt("service_time"), 
				rs.getString("order_no"), rs.getTimestamp("create_time"));
		return u;
	}
}
