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
	        	String sql = "insert into company (name, parent_id, month_expense, perfect_moment, company_code,create_time)"
	    				+ " values (?, ?, ?, ?, ?, ?)";
	               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
	               ps.setString(1, company.getName());  
	               ps.setLong(2, company.getParentId());  
	               ps.setString(3, company.getMonthExpense());
	               ps.setString(4, company.getPerfectMoment());
	               ps.setString(5, company.getCompanyCode());
	               ps.setTimestamp(6, company.getCreateTime());
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
	
	@Override
	public List<Company> getSubCompanyListByCompanyIdOrder(long companyId) {
		List<Company> list = null;
		try {
			String sql = "select * from company where parent_id=? order by company_code desc";
			list = jdbcTemplate.query(sql, new Object[]{companyId},
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<Company> getAllCompany() {
		List<Company> list = null;
		try {
			String sql = "select * from company";
			list = jdbcTemplate.query(sql,
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean updateCompanyTakeType(long companyId, String takeType) {
		String sql = "update company set take_type=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, takeType, companyId);
		logger.debug("updateCompanyTakeType result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyAccounter(long companyId, String accounterId) {
		String sql = "update company set accounter_id=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, accounterId, companyId);
		logger.debug("updateCompanyAccounter result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public List<Company> getCompanyListByAccounterId(String accounterId) {
		List<Company> list = null;
		try {
			String sql = "select * from company where accounter_id=?";
			list = jdbcTemplate.query(sql, new Object[]{accounterId},
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<Company> getCompanyAndAccounter() {
		List<Company> companyList = null;
		try {
			String sql = "select * from company c left join user u on c.id = u.company_id where u.type_id = 2";
			companyList = jdbcTemplate.query(sql,
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return companyList;
	}
	//update accounter_id from company table
	@Override
	public boolean updateAccounterForCompany(long companyId, String accounterId) {
		String sql="update company set accounter_id=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, accounterId, companyId);
		logger.debug("updateCompanyAccounter accounter_id result : {}", affectedRows);
		return  affectedRows != 0;
	}
	//get company information by name
	@Override
	public List<Company> getCompanyByName(String companyName) {
		List<Company> companyList = null;
		String sql="select * from company where name = ?";
		try{
			companyList=jdbcTemplate.query(sql, 	new Object[]{companyName},
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		}catch(Exception e){
			logger.error(e.toString());
		}
		return companyList;
	}

}
