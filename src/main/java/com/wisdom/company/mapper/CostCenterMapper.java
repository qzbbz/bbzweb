package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CostCenter;

public class CostCenterMapper implements RowMapper<CostCenter> {
	public CostCenter mapRow(ResultSet rs, int index) throws SQLException {
		CostCenter u = new CostCenter(rs.getLong("id"), rs.getLong("encode"),
				rs.getString("name"), rs.getString("alias"), 
				rs.getString("description"), rs.getTimestamp("create_time"));
		return u;
	}
}