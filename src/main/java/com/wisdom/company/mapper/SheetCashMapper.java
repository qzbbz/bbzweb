package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.SheetBalance;
import com.wisdom.common.model.SheetCash;

public class SheetCashMapper implements RowMapper<SheetCash> {
	public SheetCash mapRow(ResultSet rs, int index) throws SQLException {
		SheetCash u = new SheetCash(rs.getLong("id"),
				rs.getLong("company_id"),
				rs.getString("preparedby"),
				rs.getString("company_controller"),
				rs.getString("finance_chief"), rs.getString("tabulator"),
				rs.getDouble("sales_year"), rs.getDouble("sales_month"),
				rs.getDouble("receive_year"), rs.getDouble("receive_month"),
				rs.getDouble("buy_year"), rs.getDouble("buy_month"),
				rs.getDouble("salary_year"), rs.getDouble("salary_month"),
				rs.getDouble("tax_year"), rs.getDouble("tax_month"),
				rs.getDouble("other_pay_year"),
				rs.getDouble("other_pay_month"),
				rs.getDouble("operating_activities_year"),
				rs.getDouble("operating_activities_month"),
				rs.getDouble("take_back_year"),
				rs.getDouble("take_back_month"),
				rs.getDouble("equity_earnings_year"),
				rs.getDouble("equity_earnings_month"),
				rs.getDouble("handle_year"), rs.getDouble("handle_month"),
				rs.getDouble("investments_pay_year"),
				rs.getDouble("investments_pay_month"),
				rs.getDouble("coustruction_year"),
				rs.getDouble("coustruction_month"),
				rs.getDouble("activity_investment_year"),
				rs.getDouble("activity_investment_month"),
				rs.getDouble("loan_year"), rs.getDouble("loan_month"),
				rs.getDouble("investors_to_invest_year"),
				rs.getDouble("investors_to_invest_month"),
				rs.getDouble("payment_of_principal_year"),
				rs.getDouble("payment_of_principal_month"),
				rs.getDouble("paid_interest_year"),
				rs.getDouble("paid_interest_month"),
				rs.getDouble("distribute_profit_year"),
				rs.getDouble("distribute_profit_month"),
				rs.getDouble("financing_activity_year"),
				rs.getDouble("financing_activity_month"),
				rs.getDouble("net_increase_in_cash_year"),
				rs.getDouble("net_increase_in_cash_month"),
				rs.getDouble("cash_at_beginning_of_period_year"),
				rs.getDouble("cash_at_beginning_of_period_month"),
				rs.getDouble("ending_cash_balance_year"),
				rs.getDouble("ending_cash_balance_month"),
				rs.getString("create_time"));
		return u;
	}
}