package com.wisdom.invoice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.InvoiceApproval;

public class InvoiceApprovalMapper implements RowMapper<InvoiceApproval> {
	public InvoiceApproval mapRow(ResultSet rs, int index) throws SQLException {
		InvoiceApproval u = new InvoiceApproval(rs.getLong("id"), 
				rs.getLong("invoice_id"), rs.getString("user_id"),
				rs.getInt("status"),rs.getInt("approval_status"), rs.getTimestamp("create_time"),
				rs.getTimestamp("update_time"), rs.getLong("new_invoice_id"));
		return u;
	}
}
