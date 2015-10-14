package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.wisdom.common.model.CompanyFixedAssets;


public class CompanyFixedAssetsMapper  implements RowMapper<CompanyFixedAssets> {

	@Override
	public CompanyFixedAssets mapRow(ResultSet rs, int index) throws SQLException {
		CompanyFixedAssets cfa=new CompanyFixedAssets(rs.getLong("id"), rs.getLong("company_id"),
				rs.getString("month"), rs.getInt("category"), rs.getString("name"), 
				rs.getDouble("assets_value"), rs.getDouble("expect_remaining_value"), 
				rs.getInt("amortization_depreciation_month"), rs.getDouble("remaining_rate"),
				rs.getDouble("month_depreciation_value"),rs.getInt("amortization_month"),rs.getDouble("provision_depreciation"),
				rs.getDouble("cumulative_clepreciation"),rs.getDouble("net_worth")
				);
		return cfa;
	}

}
