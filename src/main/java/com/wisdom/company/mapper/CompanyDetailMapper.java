package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanyDetail;

public class CompanyDetailMapper implements RowMapper<CompanyDetail> {
	public CompanyDetail mapRow(ResultSet rs, int index) throws SQLException {
		CompanyDetail u = new CompanyDetail(rs.getLong("id"), rs.getLong("company_id"),
				rs.getString("company_res_file"), rs.getString("corporation"), 
				rs.getString("province"), rs.getString("city"),
				rs.getString("area"), rs.getString("extra"),
				rs.getString("org_code"),
				rs.getString("org_code_file"), rs.getString("tax_code"),
				rs.getString("tax_code_file"), rs.getString("bank_name"),
				rs.getTimestamp("create_time"));
		return u;
	}
}