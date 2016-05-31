package com.wisdom.company.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.ExpenseType;
import com.wisdom.company.dao.IExpenseTypeDao;
import com.wisdom.company.mapper.ExpenseTypeMapper;

@Repository("expenseTypeDao")
public class ExpenseTypeDaoImpl implements IExpenseTypeDao {

	private static final Logger logger = LoggerFactory
			.getLogger(ExpenseTypeDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ExpenseType getExpenseTypeCompanyId(long expenseTypeId) {
		logger.debug("expenseTypeId : {}", expenseTypeId);
		String sql = "select * from expense_type where id = ?";
		ExpenseType expenseType = null;
		try {
			expenseType = jdbcTemplate.queryForObject(sql,
					new Object[] { expenseTypeId }, new ExpenseTypeMapper());
		} catch (Exception e) {
			logger.debug("result is 0.");
		}
		return expenseType;
	}

	@Override
	public boolean addExpenseType(ExpenseType expenseType) {
		String sql = "insert into expense_type (name, create_time, hit)"
				+ " values (?, ?, 0)";
		int affectedRows = jdbcTemplate.update(
				sql,
				expenseType.getName(),
				expenseType.getCreateTime() == null ? new Timestamp(System
						.currentTimeMillis()) : expenseType.getCreateTime());
		logger.debug("addExpenseType result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteExpenseType(ExpenseType expenseType) {
		String sql = "delete from expense_type where id = ?";
		int affectedRows = jdbcTemplate.update(sql, expenseType.getId());
		logger.debug("deleteExpenseType result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateExpenseType(ExpenseType expenseType) {
		String sql = "update expense_type set name=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, expenseType.getName(),
				expenseType.getId());
		logger.debug("updateExpenseType result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public List<ExpenseType> getAllExpenseType() {
		List<ExpenseType> list = null;
		try {
			String sql = "select * from expense_type order by hit desc";
			list = jdbcTemplate.query(sql,
					new RowMapperResultSetExtractor<ExpenseType>(
							new ExpenseTypeMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean increaseExpenseTypeHit(String name) {
		String sql = "update expense_type set hit=hit+1 where name=?";
		int affectedRows = jdbcTemplate.update(sql, name);
		logger.debug("updateExpenseType result : {}", affectedRows);
		return affectedRows != 0;
	}

}
