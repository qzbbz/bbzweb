package com.weixin.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.weixin.model.WeixinWaitAuditInvoiceModel;

public class WeixinWaitAuditInvoiceModelMapper  implements RowMapper<WeixinWaitAuditInvoiceModel> {
	public WeixinWaitAuditInvoiceModel mapRow(ResultSet rs, int index) throws SQLException {
		WeixinWaitAuditInvoiceModel u = new WeixinWaitAuditInvoiceModel(rs.getTimestamp("create_time"), rs.getString("file_name"),
				rs.getLong("invoice_id"), rs.getDouble("amount"), rs.getString("type"),
				rs.getString("user_name"), rs.getInt("approval_status"), rs.getString("reasons"), rs.getString("user_id"));
		return u;
	}
}