package com.wisdom.company.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.SheetBalance;
import com.wisdom.common.model.SheetCash;
import com.wisdom.common.model.SheetSalaryTax;
import com.wisdom.company.dao.ISheetBalanceDao;
import com.wisdom.company.dao.ISheetCashDao;
import com.wisdom.company.mapper.SheetCashMapper;
import com.wisdom.company.mapper.SheetSalaryTaxMapper;

@Repository("sheetCashDao")
public class SheetCashDaoImpl implements ISheetCashDao {

	private static final Logger logger = LoggerFactory
			.getLogger(SheetCashDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public SheetCash getSheetCashDateByCompanyIdAndDate(long companyId,
			String date) {
		String sql = "select * from sheet_cash where company_id = ? and create_time = ?";
		SheetCash sheetCash = null;
		try {
			sheetCash = jdbcTemplate.queryForObject(sql, new Object[] { companyId, date },
					new SheetCashMapper());
		} catch (Exception e) {
			logger.debug("result is 0.");
		}
		return sheetCash;
	}

	@Override
	public SheetCash getNewestSheetCashDateByCompanyId(long companyId) {
		String sql = "select * from sheet_cash where company_id = ? order by create_time desc limit 1";
		SheetCash sheetCash = null;
		try {
			sheetCash = jdbcTemplate.queryForObject(sql, new Object[] { companyId},
					new SheetCashMapper());
		} catch (Exception e) {
			logger.debug("result is 0.");
		}
		return sheetCash;
	}
		
}
