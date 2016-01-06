package com.wisdom.invoice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.TestInvoice;

public class TestInvoiceMapper  implements RowMapper<TestInvoice> {
	public TestInvoice mapRow(ResultSet rs, int index) throws SQLException {
		TestInvoice u = new TestInvoice(rs.getLong("id"), rs.getLong("company_id"),
				rs.getString("file_name"), rs.getString("bill_date"), rs.getInt("is_fixed_assets"),
				rs.getTimestamp("created_time"),rs.getTimestamp("modified_time"),
				rs.getString("item_id"), rs.getString("status"), rs.getString("cost_center"), rs.getString("type"), rs.getInt("generated"), rs.getString("comment"));
		return u;
	}
}