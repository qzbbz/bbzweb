package com.wisdom.accounter.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.wisdom.accounter.dao.IAccounterIndustryDao;
import com.wisdom.accounter.mapper.AccounterIndustryMapper;
import com.wisdom.common.model.AccounterIndustry;

public class AccounterIndustryDaoImpl implements IAccounterIndustryDao {

	private static final Logger logger = LoggerFactory
			.getLogger(AccounterIndustryDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public AccounterIndustry getAccounterIndustryById(long id) {
		String sql = "select * from accounter_industry where id = ?";
		AccounterIndustry user = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new AccounterIndustryMapper());
		return user;
	}

	@Override
	public List<AccounterIndustry> getAllAccounterIndustry() {
		List<AccounterIndustry> list = null;
		try {
			String sql = "select * from accounter_industry";
			list = jdbcTemplate.query(sql, 
					new RowMapperResultSetExtractor<AccounterIndustry>(
							new AccounterIndustryMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean addAccounterIndustry(AccounterIndustry accounterIndustry) {
		String sql = "insert into accounter_industry (name, create_time)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, accounterIndustry.getName(),
				accounterIndustry.getCreateTime());
		logger.debug("addAccounterIndustry result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteAccounterIndustry(AccounterIndustry accounterIndustry) {
		String sql = "delete from accounter_industry where id = ?";
		int affectedRows = jdbcTemplate.update(sql, accounterIndustry.getId());
		logger.debug("deleteAccounterIndustry result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateAccounterIndustry(AccounterIndustry accounterIndustry) {
		String sql = "update accounter_industry set name=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, accounterIndustry.getName(),
				accounterIndustry.getId());
		logger.debug("updateAccounterIndustry result : {}", affectedRows);
		return affectedRows != 0;
	}

}
