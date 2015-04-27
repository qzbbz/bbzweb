package com.wisdom.user.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.AmountLimit;
import com.wisdom.user.dao.IAmountLimitDao;
import com.wisdom.user.mapper.AmountLimitMapper;

@Repository("amountLimitDao")
public class AmountLimitDaoImpl implements IAmountLimitDao {

	private static final Logger logger = LoggerFactory
			.getLogger(AmountLimitDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean addAmountLimit(AmountLimit amountLimit) {
		String sql = "insert into amount_limit (company_id, dept_id, role_id, limit, create_time)"
				+ " values (?, ?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, amountLimit.getCompanyId(),
				amountLimit.getDeptId(), amountLimit.getRoleId(),
				amountLimit.getLimit(), amountLimit.getCreateTime());
		logger.debug("addAmountLimit result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteAmountLimit(AmountLimit amountLimit) {
		String sql = "delete from amount_limit where company_id=? and dept_id=? and role_id = ?";
		int affectedRows = jdbcTemplate.update(sql, amountLimit.getCompanyId(),
				amountLimit.getDeptId(), amountLimit.getRoleId());
		logger.debug("deleteAmountLimit result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateAmountLimit(AmountLimit amountLimit) {
		String sql = "update amount_limit set limit=? where company_id=? and dept_id=? and role_id = ?";
		int affectedRows = jdbcTemplate.update(sql, amountLimit.getLimit(),
				amountLimit.getCompanyId(), amountLimit.getDeptId(),
				amountLimit.getRoleId());
		logger.debug("updateAmountLimit result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public AmountLimit getAmountLimit(AmountLimit amountLimit) {
		String sql = "select * from amount_limit where company_id=? and dept_id=? and role_id = ?";
		AmountLimit limit = jdbcTemplate.queryForObject(sql, new Object[] {
				amountLimit.getCompanyId(), amountLimit.getDeptId(),
				amountLimit.getRoleId() }, new AmountLimitMapper());
		return limit;
	}

}