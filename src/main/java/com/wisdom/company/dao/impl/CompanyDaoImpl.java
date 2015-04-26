package com.wisdom.company.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wisdom.common.model.Company;
import com.wisdom.company.dao.ICompanyDao;
import com.wisdom.company.mapper.CompanyMapper;

public class CompanyDaoImpl implements ICompanyDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Company getCompanyByCompanyId(long companyId) {
		String sql = "select * from company where id = ?";
		Company company = jdbcTemplate.queryForObject(sql,
				new Object[] { companyId }, new CompanyMapper());
		logger.debug("getCompanyByCompanyId");
		return company;
	}

	@Override
	public boolean addCompany(Company company) {
		String sql = "insert into company (name, parent_id, month_expense, perfect_moment, create_time)"
				+ " values (?, ?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, company.getName(),
				company.getParentId(), company.getMonthExpense(),
				company.getPerfectMoment(), company.getCreateTime());
		logger.debug("addCompany result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteCompany(Company company) {
		String sql = "delete from company where id = ?";
		int affectedRows = jdbcTemplate.update(sql, company.getId());
		logger.debug("deleteCompany result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompany(Company company) {
		String sql = "update company set name=?, parent_id=?, month_expense=?, perfect_moment=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, company.getName(),
				company.getParentId(), company.getMonthExpense(),
				company.getPerfectMoment(), company.getId());
		logger.debug("updateCompany result : {}", affectedRows);
		return affectedRows != 0;
	}

}
