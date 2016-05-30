package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanyBankRes;

public class CompanyBankResMapper implements RowMapper<CompanyBankRes> {
	public CompanyBankRes mapRow(ResultSet rs, int index) throws SQLException {
		CompanyBankRes u = new CompanyBankRes(rs.getLong("id"), rs.getLong("company_id"),
				rs.getString("date"), rs.getString("ide_name"), 
				rs.getString("ide_bank_name"), rs.getString("ide_account"),
				rs.getString("ide_date"), rs.getDouble("ide_amount"), rs.getString("ide_type"),
				rs.getString("file_name"), rs.getInt("identify_status"), rs.getTimestamp("create_time"));
		return u;
	}
}
