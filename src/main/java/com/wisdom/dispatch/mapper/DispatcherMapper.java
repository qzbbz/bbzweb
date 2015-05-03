package com.wisdom.dispatch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Dispatcher;

public class DispatcherMapper implements RowMapper<Dispatcher> {
	public Dispatcher mapRow(ResultSet rs, int index) throws SQLException {
		Dispatcher u = new Dispatcher(rs.getLong("id"), rs.getString("user_id"),rs.getString("user_name"),
				rs.getLong("object_type_id"), rs.getLong("invoice_id"), 
				rs.getInt("channel_type_id"), rs.getInt("status"),
				rs.getString("reciever"), rs.getString("openId"),rs.getTimestamp("create_time"),
				rs.getTimestamp("update_time"));
		return u;
	}
}
