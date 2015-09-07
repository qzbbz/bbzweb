package com.wisdom.contactus.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Attachment;
import com.wisdom.common.model.ContactUs;

public class ContactUsMapper implements RowMapper<ContactUs> {
	public ContactUs mapRow(ResultSet rs, int index) throws SQLException {
		ContactUs u = new ContactUs(rs.getInt("id"), 
				rs.getString("user_name"), rs.getString("user_phone"),
				rs.getString("user_company_name"),rs.getTimestamp("create_time"));
		return u;
	}
}
