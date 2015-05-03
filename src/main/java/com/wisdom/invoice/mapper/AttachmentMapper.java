package com.wisdom.invoice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Attachment;

public class AttachmentMapper implements RowMapper<Attachment> {
	public Attachment mapRow(ResultSet rs, int index) throws SQLException {
		Attachment u = new Attachment(rs.getLong("id"), 
				rs.getLong("invoice_id"), rs.getString("image"),
				rs.getTimestamp("create_time"));
		return u;
	}
}
