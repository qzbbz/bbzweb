package com.wisdom.area.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.ShxCity;

public class ShxCityMapper  implements RowMapper<ShxCity> {
	public ShxCity mapRow(ResultSet rs, int index) throws SQLException {
		ShxCity u = new ShxCity(rs.getInt("id"), rs.getString("cityID"),
				rs.getString("city"), rs.getString("father"));
		return u;
	}
}