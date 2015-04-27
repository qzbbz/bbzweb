package com.wisdom.area.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.ShxProvince;

public class ShxProvinceMapper  implements RowMapper<ShxProvince> {
	public ShxProvince mapRow(ResultSet rs, int index) throws SQLException {
		ShxProvince u = new ShxProvince(rs.getInt("id"), rs.getString("provinceID"),
				rs.getString("province"));
		return u;
	}
}