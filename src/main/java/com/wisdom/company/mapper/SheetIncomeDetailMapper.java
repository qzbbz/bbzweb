package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.SheetIncomeDetail;

public class SheetIncomeDetailMapper implements RowMapper<SheetIncomeDetail> {
	public SheetIncomeDetail mapRow(ResultSet rs, int index) throws SQLException {
		SheetIncomeDetail s = new SheetIncomeDetail(rs.getLong("id") ,rs.getLong("company_id"), 
				rs.getString("date_time"), rs.getString("voucher_type"), rs.getString("voucher_no"),
				rs.getString("attachment_nums"), rs.getString("abstracts"),rs.getString("subject"),rs.getString("borrower"),
				rs.getString("lender"),rs.getString("touching"), rs.getString("pchk"),
				rs.getString("customer"), rs.getString("project"));
		return s;
	}
}
