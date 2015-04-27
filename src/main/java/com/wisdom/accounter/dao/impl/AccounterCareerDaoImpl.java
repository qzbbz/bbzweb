package com.wisdom.accounter.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.wisdom.accounter.dao.IAccounterCareerDao;
import com.wisdom.accounter.mapper.AccounterCareerMapper;
import com.wisdom.common.model.AccounterCareer;

public class AccounterCareerDaoImpl implements IAccounterCareerDao {

	private static final Logger logger = LoggerFactory
			.getLogger(AccounterCareerDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public AccounterCareer getAccounterCareerById(long id) {
		String sql = "select * from accounter_career where id = ?";
		AccounterCareer user = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new AccounterCareerMapper());
		return user;
	}

	@Override
	public List<AccounterCareer> getAllAccounterCareer() {
		List<AccounterCareer> list = null;
		try {
			String sql = "select * from accounter_career";
			list = jdbcTemplate.query(sql, 
					new RowMapperResultSetExtractor<AccounterCareer>(
							new AccounterCareerMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean addAccounterCareer(AccounterCareer accounterCareer) {
		String sql = "insert into accounter_career (name, create_time)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, accounterCareer.getName(),
				accounterCareer.getCreateTime());
		logger.debug("addAccounterCareer result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteAccounterCareer(AccounterCareer accounterCareer) {
		String sql = "delete from accounter_career where id = ?";
		int affectedRows = jdbcTemplate.update(sql, accounterCareer.getId());
		logger.debug("deleteAccounterCareer result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateAccounterCareer(AccounterCareer accounterCareer) {
		String sql = "update accounter_career set name=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, accounterCareer.getName(),
				accounterCareer.getId());
		logger.debug("updateAccounterCareer result : {}", affectedRows);
		return affectedRows != 0;
	}

}
