package com.wisdom.invoice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Invoice;

public class InvoiceMapper implements RowMapper<Invoice> {
	public Invoice mapRow(ResultSet rs, int index) throws SQLException {
		Invoice u = new Invoice(rs.getLong("id"), rs.getString("invoice_code"),rs.getString("title"),
				rs.getInt("expense_type_id"), rs.getInt("commit_status"),
				rs.getDouble("amount"), rs.getString("detail_desc"),rs.getTimestamp("date"),
				rs.getString("cost_center"), rs.getInt("identify_status"),
				rs.getTimestamp("create_time"));
		return u;
	}
	
	
}
