package com.wisdom.company.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Dept;
import com.wisdom.common.model.SheetBalance;
import com.wisdom.common.model.SheetCash;
import com.wisdom.common.model.SheetIncome;
import com.wisdom.common.model.SheetSalaryTax;
import com.wisdom.company.dao.ISheetBalanceDao;
import com.wisdom.company.dao.ISheetCashDao;
import com.wisdom.company.dao.ISheetIncomeDao;
import com.wisdom.company.dao.ISheetSalaryTaxDao;
import com.wisdom.company.mapper.DeptMapper;
import com.wisdom.company.mapper.SheetSalaryTaxMapper;

@Repository("sheetSalaryTaxDao")
public class SheetSalaryTaxDaoImpl implements ISheetSalaryTaxDao {

	private static final Logger logger = LoggerFactory
			.getLogger(SheetSalaryTaxDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public SheetSalaryTax getSheetSalaryTaxDateByCompanyIdAndDate(
			long companyId, String date) {
		String sql = "select * from sheet_salary_tax where company_id = ? and create_time = ?";
		SheetSalaryTax sheetSalaryTax = null;
		try {
			sheetSalaryTax = jdbcTemplate.queryForObject(sql, new Object[] { companyId, date },
					new SheetSalaryTaxMapper());
		} catch (Exception e) {
			logger.debug("result is 0.");
		}
		return sheetSalaryTax;
	}

	@Override
	public SheetSalaryTax getNewestSheetSalaryTaxDateByCompanyId(long companyId) {
		String sql = "select * from sheet_salary_tax where company_id = ? order by create_time desc limit 1";
		SheetSalaryTax sheetSalaryTax = null;
		try {
			sheetSalaryTax = jdbcTemplate.queryForObject(sql, new Object[] { companyId},
					new SheetSalaryTaxMapper());
		} catch (Exception e) {
			logger.debug("result is 0.");
		}
		return sheetSalaryTax;
	}
		
}
