package com.wisdom.area.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.wisdom.common.model.ShxArea;

public class ShxAreaMapper implements RowMapper<ShxArea> {
	public ShxArea mapRow(ResultSet rs, int index) throws SQLException {
		ShxArea u = new ShxArea(rs.getInt("id"), rs.getString("areaID"),
				rs.getString("area"), rs.getString("father"));
		return u;
	}
}
