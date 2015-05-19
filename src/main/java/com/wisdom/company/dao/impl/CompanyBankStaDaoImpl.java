package com.wisdom.company.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.CompanyBankSta;
import com.wisdom.company.dao.ICompanyBankStaDao;
import com.wisdom.company.mapper.CompanyBankStaMapper;

@Repository("companyBankStaDao")
public class CompanyBankStaDaoImpl implements ICompanyBankStaDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyBankStaDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public CompanyBankSta getCompanyBankStaByCompanyId(long companyId) {
		logger.debug("companyId : {}", companyId);
		String sql = "select * from company_bank_sta where id = ?";
		CompanyBankSta companyBankSta = null;
		try {
			companyBankSta = jdbcTemplate.queryForObject(sql,
				new Object[] { companyId }, new CompanyBankStaMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}", e.toString());
		}
		return companyBankSta;
	}

	@Override
	public long addCompanyBankSta(CompanyBankSta companyBankSta) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		long id = jdbcTemplate.update(new PreparedStatementCreator() {  
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
	        	String sql = "insert into company_bank_sta (company_id, date, file_name, identify_status, create_time)"
	    				+ " values (?, ?, ?, ?, ?)";
	               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
	               ps.setLong(1, companyBankSta.getCompanyId() == null ? 0 : companyBankSta.getCompanyId());  
	               ps.setString(2, companyBankSta.getDate() == null ? "" : companyBankSta.getDate());  
	               ps.setString(3, companyBankSta.getFileName() == null ? "" : companyBankSta.getFileName());
	               ps.setInt(4, companyBankSta.getIdentifyStatus() == null ? 0: companyBankSta.getIdentifyStatus());
	               ps.setTimestamp(5, companyBankSta.getCreateTime() == null ? new Timestamp(System.currentTimeMillis()) : companyBankSta.getCreateTime());
	               return ps;  
	        }  
	    }, keyHolder);
		logger.debug("addCompany result : {}", keyHolder.getKey().longValue());
		return keyHolder.getKey().longValue();
	}

	@Override
	public boolean deleteCompanyBankStaByCompanyId(long companyId) {
		String sql = "delete from company_bank_sta where company_id = ?";
		int affectedRows = jdbcTemplate.update(sql, companyId);
		logger.debug("deleteCompanyBankStaByCompanyId result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyBankSta(CompanyBankSta companyBankSta) {
		return false;
	}

	@Override
	public boolean updateCompanyBankStaIdentifyStatusById(
			long id, int status) {
		String sql = "update company_bank_sta set identify_status=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, status, id);
		logger.debug("updateCompanyBankStaIdentifyStatusById result : {}", affectedRows);
		return affectedRows != 0;
	}

}
