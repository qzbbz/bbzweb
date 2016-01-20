package com.wisdom.invoice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.TestInvoice;
import com.wisdom.common.model.TestInvoiceRecord;

public class TestInvoiceRecordMapper  implements RowMapper<TestInvoiceRecord>{
	public TestInvoiceRecord mapRow(ResultSet rs, int index) throws SQLException {
		TestInvoiceRecord u = new TestInvoiceRecord(rs.getLong("invoice_id"), rs.getLong("company_id"),
				rs.getString("type"), rs.getDouble("amount"), rs.getDouble("tax"), rs.getTimestamp("created_time"), rs.getInt("is_fixed_assets"),
				rs.getString("bill_date"),rs.getString("supplier_name"), rs.getString("file_name"), rs.getString("status"));
		return u;
	}
}