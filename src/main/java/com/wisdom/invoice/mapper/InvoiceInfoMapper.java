package com.wisdom.invoice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.InvoiceInfo;

public class InvoiceInfoMapper implements RowMapper<InvoiceInfo>{

	/**
	 * private Long invoiceId;
	private String invoiceCode;
	private String title;
	private Integer expenseTypeId;
	private Double amount;
	private String desc;
	private Timestamp date;
	private String costCenter;
	private Integer processStatus; 
	private Integer approvalStatus;
	private String approver;
	private String submitter;
	 */
	
	@Override
	public InvoiceInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		InvoiceInfo u = new InvoiceInfo(rs.getLong("invoice_id"), rs.getString("invoice_code"),rs.getString("title"),
				rs.getInt("expense_type_id"),rs.getDouble("amount"),rs.getString("detail_desc"),
				rs.getTimestamp("date"),rs.getString("cost_center"),rs.getInt("process_status"),
				rs.getInt("approval_status"),
				rs.getString("approval_id"),
				rs.getString("user_id"));
		return u;
	}

}
