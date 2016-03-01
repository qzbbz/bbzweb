package com.wisdom.sales.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.SalesComment;

public class SalesCommentMapper  implements RowMapper<SalesComment> {
	public SalesComment mapRow(ResultSet rs, int index) throws SQLException {
		SalesComment u = new SalesComment(rs.getInt("id"),
				rs.getInt("sales_id"), rs.getString("comment"), rs.getTimestamp("updated_time"));
		return u;
	}
}

