package com.wisdom.invoice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Invoice;

public class InvoiceMapper implements RowMapper<Invoice> {
	public Invoice mapRow(ResultSet rs, int index) throws SQLException {
		Invoice u = new Invoice(rs.getLong("id"), rs.getString("title"),
				rs.getDouble("amount"), rs.getTimestamp("date"),
				rs.getTimestamp("create_time"));
		return u;
	}
	
	
}
