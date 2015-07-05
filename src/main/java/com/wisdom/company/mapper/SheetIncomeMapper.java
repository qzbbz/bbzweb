package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.SheetBalance;
import com.wisdom.common.model.SheetCash;
import com.wisdom.common.model.SheetIncome;

public class SheetIncomeMapper implements RowMapper<SheetIncome> {
	public SheetIncome mapRow(ResultSet rs, int index) throws SQLException {
		SheetIncome u = new SheetIncome(rs.getLong("id"),
				rs.getLong("company_id"),
				rs.getString("preparedby"),
				rs.getString("company_controller"),
				rs.getString("finance_chief"), rs.getString("tabulator"),
				rs.getDouble("operating_receipt_year"),
				rs.getDouble("operating_receipt_month"),
				rs.getDouble("cost_in_business_year"),
				rs.getDouble("cost_in_business_month"),
				rs.getDouble("business_tariff_and_annex_year"),
				rs.getDouble("business_tariff_and_annex_month"),
				rs.getDouble("excise_tax_year"),
				rs.getDouble("excise_tax_month"),
				rs.getDouble("sales_tax_year"),
				rs.getDouble("sales_tax_month"),
				rs.getDouble("urban_maintenance_and_construction_tax_year"),
				rs.getDouble("urban_maintenance_and_construction_month"),
				rs.getDouble("resource_tax_year"),
				rs.getDouble("resource_tax_month"),
				rs.getDouble("land_value_increment_tax_year"),
				rs.getDouble("land_value_increment_tax_month"),
				rs.getDouble("land_house_tax_year"),
				rs.getDouble("land_house_tax_month"),
				rs.getDouble("education_year"),
				rs.getDouble("education_month"), rs.getDouble("sales_year"),
				rs.getDouble("sales_month"), rs.getDouble("maintenance_year"),
				rs.getDouble("maintenance_month"),
				rs.getDouble("advertise_year"),
				rs.getDouble("advertise_month"), rs.getDouble("manage_year"),
				rs.getDouble("manage_month"),
				rs.getDouble("establishment_charges_year"),
				rs.getDouble("establishment_charges_month"),
				rs.getDouble("business_entertainment_year"),
				rs.getDouble("business_entertainment_month"),
				rs.getDouble("research_year"), rs.getDouble("research_month"),
				rs.getDouble("finance_year"), rs.getDouble("finance_month"),
				rs.getDouble("interest_year"), rs.getDouble("interest_month"),
				rs.getDouble("equity_earnings_year"),
				rs.getDouble("equity_earnings_month"),
				rs.getDouble("operating_profit_year"),
				rs.getDouble("operating_profit_month"),
				rs.getDouble("nonbusiness_income_year"),
				rs.getDouble("nonbusiness_income_month"),
				rs.getDouble("government_grants_year"),
				rs.getDouble("government_grants_month"),
				rs.getDouble("non_business_expenditure_year"),
				rs.getDouble("non_business_expenditure_month"),
				rs.getDouble("loss_on_bad_debt_year"),
				rs.getDouble("loss_on_bad_debt_month"),
				rs.getDouble("loss_on_long_term_bonds_year"),
				rs.getDouble("loss_on_long_term_bonds_month"),
				rs.getDouble("loss_on_long_term_stock_year"),
				rs.getDouble("loss_on_long_term_stock_month"),
				rs.getDouble("loss_on_long_term_natural_calamities_year"),
				rs.getDouble("loss_on_long_term_natural_calamities_month"),
				rs.getDouble("tax_delay_charge_year"),
				rs.getDouble("tax_delay_charge_month"),
				rs.getDouble("total_profit_year"),
				rs.getDouble("total_profit_month"),
				rs.getDouble("income_tax_expense_year"),
				rs.getDouble("income_tax_expense_month"),
				rs.getDouble("net_margin_year"),
				rs.getDouble("net_margin_month"),
				rs.getString("create_time"));
		return u;
	}
}
