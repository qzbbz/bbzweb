package com.wisdom.accounter.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.AccounterCareer;
import com.wisdom.common.model.CustomerManagement;

public class CustomerManagementMapper implements RowMapper<CustomerManagement> {
	public CustomerManagement mapRow(ResultSet rs, int index) throws SQLException {
		CustomerManagement c = new CustomerManagement(rs.getString("name"), rs.getString("create_time"),rs.getString("tax_status"),
				rs.getTimestamp("expired_time"), rs.getString("user_name"), rs.getInt("comment_count"), rs.getString("taobao_accounter"),
				rs.getLong("id"));
		return c;
	}
}
