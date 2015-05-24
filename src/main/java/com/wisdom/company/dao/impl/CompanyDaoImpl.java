package com.wisdom.company.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.ExpenseType;
import com.wisdom.company.dao.ICompanyDao;
import com.wisdom.company.mapper.CompanyMapper;
import com.wisdom.company.mapper.ExpenseTypeMapper;

@Repository("companyDao")
public class CompanyDaoImpl implements ICompanyDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Company getCompanyByCompanyId(long companyId) {
		logger.debug("companyId : {}", companyId);
		String sql = "select * from company where id = ?";
		Company company = null;
		try {
			company = jdbcTemplate.queryForObject(sql,
				new Object[] { companyId }, new CompanyMapper());
		} catch(Exception e) {
			logger.debug("result is 0.");
		}
		return company;
	}

	@Override
	public long addCompany(Company company) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		long id = jdbcTemplate.update(new PreparedStatementCreator() {  
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
	        	String sql = "insert into company (name, parent_id, month_expense, perfect_moment, create_time)"
	    				+ " values (?, ?, ?, ?, ?)";
	               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
	               ps.setString(1, company.getName());  
	               ps.setLong(2, company.getParentId());  
	               ps.setInt(3, company.getMonthExpense());
	               ps.setString(4, company.getPerfectMoment());
	               ps.setTimestamp(5, company.getCreateTime());
	               return ps;  
	        }  
	    }, keyHolder);
		logger.debug("addCompany result : {}", keyHolder.getKey().longValue());
		return keyHolder.getKey().longValue();
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

	@Override
	public boolean updateCompanyName(String companyName, long companyId) {
		String sql = "update company name set name=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, companyName, companyId);
		logger.debug("updateCompanyName result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public List<Company> getSubCompanyListByCompanyId(long companyId) {
		List<Company> list = null;
		try {
			String sql = "select * from company where parent_id=?";
			list = jdbcTemplate.query(sql, new Object[]{companyId},
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

}
