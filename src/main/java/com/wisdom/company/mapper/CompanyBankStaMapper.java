package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.CompanyBankSta;

public class CompanyBankStaMapper implements RowMapper<CompanyBankSta> {
	public CompanyBankSta mapRow(ResultSet rs, int index) throws SQLException {
		CompanyBankSta u = new CompanyBankSta(rs.getLong("id"), rs.getLong("company_id"),
				rs.getString("date"), rs.getString("ide_name"), 
				rs.getString("ide_bank_name"), rs.getString("ide_account"),
				rs.getString("ide_date"), rs.getDouble("ide_amount"), rs.getString("ide_type"),
				rs.getString("file_name"), rs.getInt("identify_status"), rs.getTimestamp("create_time"));
		return u;
	}
}
