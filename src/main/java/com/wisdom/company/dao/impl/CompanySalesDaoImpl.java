package com.wisdom.company.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanySales;
import com.wisdom.company.dao.ICompanyBillDao;
import com.wisdom.company.dao.ICompanySalesDao;
import com.wisdom.company.mapper.CompanyBillMapper;
import com.wisdom.company.mapper.CompanySalesMapper;

@Repository("companySalesDao")
public class CompanySalesDaoImpl implements ICompanySalesDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanySalesDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CompanySales getCompanySalesById(long id) {
		logger.debug("companyId : {}", id);
		String sql = "select * from company_sales where id = ?";
		CompanySales companySales = null;
		try {
			companySales = jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new CompanySalesMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}", e.toString());
		}
		return companySales;
	}

	@Override
	public List<CompanySales> getAllCompanySales(long companyId) {
		List<CompanySales> list = null;
		try {
			String sql = "select * from company_sales where company_id=?";
			list = jdbcTemplate.query(sql, new Object[]{companyId}, 
					new RowMapperResultSetExtractor<CompanySales>(
							new CompanySalesMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<CompanySales> getAllCompanySalesByDate(long companyId, String date) {
		List<CompanySales> list = null;
		try {
			String sql = "select * from company_sales where company_id=? and create_time like '%"+date+"%'";
			logger.debug("sql : {}", sql);
			list = jdbcTemplate.query(sql, new Object[]{companyId},
					new RowMapperResultSetExtractor<CompanySales>(
							new CompanySalesMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public long addCompanySales(CompanySales companySales) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {  
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
	        	String sql = "insert into company_sales (company_id, file_name, create_time)"
	    				+ " values (?, ?, ?)";
	               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
	               ps.setLong(1, companySales.getCompanyId() == null ? 0 : companySales.getCompanyId());  
	               ps.setString(2, companySales.getFileName() == null ? "" : companySales.getFileName());
	               ps.setTimestamp(3, companySales.getCreateTime() == null ? new Timestamp(System.currentTimeMillis()) : companySales.getCreateTime());
	               return ps;  
	        }  
	    }, keyHolder);
		logger.debug("addCompanySales result : {}", keyHolder.getKey().longValue());
		return keyHolder.getKey().longValue();
	}

	@Override
	public boolean deleteCompanySalesById(long id) {
		String sql = "delete from company_sales where id = ?";
		int affectedRows = jdbcTemplate.update(sql, id);
		logger.debug("deleteCompanySalesById result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanySales(double amount, String type, long id) {
		String sql = "update company_sales set amount=? and type=? where id=?";
		int affectedRows = jdbcTemplate.update(sql,
				amount, type, id);
		logger.debug("updateCompanySales result : {}", affectedRows);
		return affectedRows != 0;
	}

}
