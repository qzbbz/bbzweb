package com.wisdom.invoice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.UserInvoice;

public class UserInvoiceMapper  implements RowMapper<UserInvoice> {
	public UserInvoice mapRow(ResultSet rs, int index) throws SQLException {
		UserInvoice u = new UserInvoice(rs.getLong("id"), rs.getString("user_id"),
				rs.getLong("invoice_id"), rs.getInt("status"), 
				rs.getTimestamp("update_time"), rs.getTimestamp("create_time"));
		return u;
	}
}