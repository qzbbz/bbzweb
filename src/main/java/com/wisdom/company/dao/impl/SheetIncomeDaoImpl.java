package com.wisdom.company.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.SheetBalance;
import com.wisdom.common.model.SheetCash;
import com.wisdom.common.model.SheetIncome;
import com.wisdom.common.model.SheetSalaryTax;
import com.wisdom.company.dao.ISheetBalanceDao;
import com.wisdom.company.dao.ISheetCashDao;
import com.wisdom.company.dao.ISheetIncomeDao;
import com.wisdom.company.mapper.SheetIncomeMapper;
import com.wisdom.company.mapper.SheetSalaryTaxMapper;

@Repository("sheetIncomeDao")
public class SheetIncomeDaoImpl implements ISheetIncomeDao {

	private static final Logger logger = LoggerFactory
			.getLogger(SheetIncomeDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public SheetIncome getSheetIncomeDateByCompanyIdAndDate(long companyId,
			String date) {
		String sql = "select * from sheet_income where company_id = ? and create_time = ?";
		SheetIncome sheetIncome = null;
		try {
			sheetIncome = jdbcTemplate.queryForObject(sql, new Object[] { companyId, date },
					new SheetIncomeMapper());
		} catch (Exception e) {
			logger.debug("result is 0.");
		}
		return sheetIncome;
	}

	@Override
	public SheetIncome getNewestSheetIncomeDateByCompanyId(long companyId) {
		String sql = "select * from sheet_income where company_id = ? order by create_time desc limit 1";
		SheetIncome sheetIncome = null;
		try {
			sheetIncome = jdbcTemplate.queryForObject(sql, new Object[] { companyId},
					new SheetIncomeMapper());
		} catch (Exception e) {
			logger.debug("result is 0.");
		}
		return sheetIncome;
	}
		
}
