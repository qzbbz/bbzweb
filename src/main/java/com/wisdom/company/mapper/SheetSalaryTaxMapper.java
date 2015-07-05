package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.SheetBalance;
import com.wisdom.common.model.SheetCash;
import com.wisdom.common.model.SheetSalaryTax;

public class SheetSalaryTaxMapper implements RowMapper<SheetSalaryTax> {
	public SheetSalaryTax mapRow(ResultSet rs, int index) throws SQLException {
		SheetSalaryTax u = new SheetSalaryTax(rs.getLong("id"),
				rs.getLong("company_id"),
				rs.getString("preparedby"),
				rs.getString("company_controller"),
				rs.getString("finance_chief"), rs.getString("tabulator"),
				rs.getDouble("salery_end"), rs.getDouble("salery_begin"),
				rs.getDouble("bonus_end"), rs.getDouble("bonus_begin"),
				rs.getDouble("welfare_end"), rs.getDouble("welfare_begin"),
				rs.getDouble("insurance_end"), rs.getDouble("insurance_begin"),
				rs.getDouble("funds_end"), rs.getDouble("funds_begin"),
				rs.getDouble("sociaty_end"), rs.getDouble("sociaty_begin"),
				rs.getDouble("education_end"), rs.getDouble("education_begin"),
				rs.getDouble("no_cash_welfare_end"),
				rs.getDouble("no_cash_welfare_begin"),
				rs.getDouble("dismiss_end"), rs.getDouble("dismiss_begin"),
				rs.getDouble("other_end"), rs.getDouble("other_begin"),
				rs.getDouble("salary_total_end"),
				rs.getDouble("salary_total_begin"),
				rs.getDouble("value_added_tax_end"),
				rs.getDouble("value_added_tax_begin"),
				rs.getDouble("excise_tax_end"),
				rs.getDouble("excise_tax_begin"),
				rs.getDouble("business_tax_end"),
				rs.getDouble("business_tax_begin"),
				rs.getDouble("urban_maintenance_and_construction_tax_end"),
				rs.getDouble("urban_maintenance_and_construction_tax_begin"),
				rs.getDouble("business_income_taxes_end"),
				rs.getDouble("business_income_taxes_begin"),
				rs.getDouble("resource_tax_end"),
				rs.getDouble("resource_tax_begin"),
				rs.getDouble("land_value_increment_tax_end"),
				rs.getDouble("land_value_increment_tax_begin"),
				rs.getDouble("urban_land_use_tax_end"),
				rs.getDouble("urban_land_use_tax_begin"),
				rs.getDouble("building_taxes_end"),
				rs.getDouble("building_taxes_begin"),
				rs.getDouble("vehicle_and_vessel_tax_end"),
				rs.getDouble("vehicle_and_vessel_tax_begin"),
				rs.getDouble("attach_education_end"),
				rs.getDouble("attach_education_begin"),
				rs.getDouble("mineral_resources_end"),
				rs.getDouble("mineral_resources_begin"),
				rs.getDouble("sewage_charge_end"),
				rs.getDouble("sewage_charge_begin"),
				rs.getDouble("income_tax_for_individuals_end"),
				rs.getDouble("income_tax_for_individuals_begin"),
				rs.getDouble("tax_total_end"), rs.getDouble("tax_total_begin"),
				rs.getString("create_time"));
		return u;
	}
}
