package com.wisdom.company.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.SheetBalance;
import com.wisdom.common.model.SheetSalaryTax;
import com.wisdom.company.dao.ISheetBalanceDao;
import com.wisdom.company.mapper.SheetBalanceMapper;
import com.wisdom.company.mapper.SheetSalaryTaxMapper;

@Repository("sheetBalanceDao")
public class SheetBalanceDaoImpl implements ISheetBalanceDao {

	private static final Logger logger = LoggerFactory
			.getLogger(SheetBalanceDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public SheetBalance getSheetBalanceDateByCompanyIdAndDate(long companyId,
			String date) {
		String sql = "select * from sheet_balance where company_id = ? and create_time = ?";
		SheetBalance sheetBalance = null;
		try {
			sheetBalance = jdbcTemplate.queryForObject(sql, new Object[] { companyId, date },
					new SheetBalanceMapper());
		} catch (Exception e) {
			logger.debug("result is 0.");
		}
		return sheetBalance;
	}

	@Override
	public SheetBalance getNewestSheetBalanceDateByCompanyId(long companyId) {
		String sql = "select * from sheet_balance where company_id = ? order by create_time desc limit 1";
		SheetBalance sheetBalance = null;
		try {
			sheetBalance = jdbcTemplate.queryForObject(sql, new Object[] { companyId},
					new SheetBalanceMapper());
		} catch (Exception e) {
			logger.debug("result is 0.");
		}
		return sheetBalance;
	}
		
}
