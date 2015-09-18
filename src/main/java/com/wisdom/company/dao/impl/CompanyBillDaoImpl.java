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
import com.wisdom.company.dao.ICompanyBillDao;
import com.wisdom.company.mapper.CompanyBillMapper;

@Repository("companyBillDao")
public class CompanyBillDaoImpl implements ICompanyBillDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyBillDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CompanyBill getCompanyBillById(long id) {
		logger.debug("companyId : {}", id);
		String sql = "select * from company_bill where id = ?";
		CompanyBill companyBill = null;
		try {
			companyBill = jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new CompanyBillMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}", e.toString());
		}
		return companyBill;
	}

	@Override
	public List<CompanyBill> getAllCompanyBill(long companyId) {
		List<CompanyBill> list = null;
		try {
			String sql = "select * from company_bill where company_id=?";
			list = jdbcTemplate.query(sql, new Object[]{companyId}, 
					new RowMapperResultSetExtractor<CompanyBill>(
							new CompanyBillMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<CompanyBill> getAllCompanyBillByDate(long companyId, String date) {
		List<CompanyBill> list = null;
		try {
			String sql = "select * from company_bill where company_id=? and create_time like '%"+date+"%'";
			logger.debug("sql : {}", sql);
			list = jdbcTemplate.query(sql, new Object[]{companyId},
					new RowMapperResultSetExtractor<CompanyBill>(
							new CompanyBillMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public long addCompanyBill(CompanyBill companyBill) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {  
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
	        	String sql = "insert into company_bill (company_id, file_name, bill_date, create_time)"
	    				+ " values (?, ?, ?, ?)";
	               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
	               ps.setLong(1, companyBill.getCompanyId() == null ? 0 : companyBill.getCompanyId());  
	               ps.setString(2, companyBill.getFileName() == null ? "" : companyBill.getFileName());
	               ps.setString(3, companyBill.getBillDate() == null ? "" : companyBill.getBillDate());
	               ps.setTimestamp(4, companyBill.getCreateTime() == null ? new Timestamp(System.currentTimeMillis()) : companyBill.getCreateTime());
	               return ps;  
	        }  
	    }, keyHolder);
		logger.debug("addCompanyBill result : {}", keyHolder.getKey().longValue());
		return keyHolder.getKey().longValue();
	}

	@Override
	public boolean deleteCompanyBillById(long id) {
		String sql = "delete from company_bill where id = ?";
		int affectedRows = jdbcTemplate.update(sql, id);
		logger.debug("deleteCompanyBillById result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyBill(double amount, String type, long id) {
		String sql = "update company_bill set amount=?, type=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, amount, type, id);
		logger.debug("updateCompanyBill result : {}", affectedRows);
		return affectedRows != 0;
	}

}
