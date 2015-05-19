package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanyBankSta;

public class CompanyBankStaMapper implements RowMapper<CompanyBankSta> {
	public CompanyBankSta mapRow(ResultSet rs, int index) throws SQLException {
		CompanyBankSta u = new CompanyBankSta(rs.getLong("id"), rs.getLong("company_id"),
				rs.getString("date"), rs.getString("file_name"),
				rs.getInt("identify_status"), rs.getTimestamp("create_time"));
		return u;
	}
}
