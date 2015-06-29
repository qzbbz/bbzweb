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
	public CompanyBankSta getCompanyBankStaById(long id) {
		logger.debug("companyId : {}", id);
		String sql = "select * from company_bank_sta where id = ?";
		CompanyBankSta companyBankSta = null;
		try {
			companyBankSta = jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new CompanyBankStaMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}", e.toString());
		}
		return companyBankSta;
	}

	@Override
	public long addCompanyBankSta(CompanyBankSta companyBankSta) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {  
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
	        	String sql = "insert into company_bank_sta (company_id, date, ide_name, ide_bank_name, ide_account, file_name, identify_status, create_time)"
	    				+ " values (?, ?, ?, ?, ?,?,?,?)";
	               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
	               ps.setLong(1, companyBankSta.getCompanyId() == null ? 0 : companyBankSta.getCompanyId());  
	               ps.setString(2, companyBankSta.getDate() == null ? "" : companyBankSta.getDate());
	               ps.setString(3, companyBankSta.getIdeName() == null ? "" : companyBankSta.getIdeName());
	               ps.setString(4, companyBankSta.getIdeBankName() == null ? "" : companyBankSta.getIdeBankName());
	               ps.setString(5, companyBankSta.getIdeAccount() == null ? "" : companyBankSta.getIdeAccount());
	               ps.setString(6, companyBankSta.getFileName() == null ? "" : companyBankSta.getFileName());
	               ps.setInt(7, companyBankSta.getIdentifyStatus() == null ? 0: companyBankSta.getIdentifyStatus());
	               ps.setTimestamp(8, companyBankSta.getCreateTime() == null ? new Timestamp(System.currentTimeMillis()) : companyBankSta.getCreateTime());
	               return ps;  
	        }  
	    }, keyHolder);
		logger.debug("addCompany result : {}", keyHolder.getKey().longValue());
		return keyHolder.getKey().longValue();
	}

	@Override
	public boolean deleteCompanyBankStaById(long id) {
		String sql = "delete from company_bank_sta where id = ?";
		int affectedRows = jdbcTemplate.update(sql, id);
		logger.debug("deleteCompanyBankStaByCompanyId result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyBankSta(CompanyBankSta companyBankSta) {
		String sql = "update company_bank_sta set ide_name=?, id_bank_name=?, ide_account=?, ide_date=? where id=?";
		logger.debug("updateCompanyBankSta : {}", companyBankSta.toString());
		int affectedRows = jdbcTemplate.update(sql,
				companyBankSta.getIdeName() == null ? "" : companyBankSta.getIdeName(),
				companyBankSta.getIdeBankName() == null ? "" : companyBankSta.getIdeBankName(),
				companyBankSta.getIdeAccount() == null ? "" : companyBankSta.getIdeAccount(),
				companyBankSta.getIdeDate() == null ? "" : companyBankSta.getIdeDate(),
				companyBankSta.getId());
		logger.debug("updateCompanyBankSta result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyBankStaIdentifyStatusById(
			long id, int status) {
		String sql = "update company_bank_sta set identify_status=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, status, id);
		logger.debug("updateCompanyBankStaIdentifyStatusById result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public List<CompanyBankSta> getAllCompanyBankSta(long companyId) {
		List<CompanyBankSta> list = null;
		try {
			String sql = "select * from company_bank_sta where company_id=?";
			list = jdbcTemplate.query(sql, new Object[]{companyId}, 
					new RowMapperResultSetExtractor<CompanyBankSta>(
							new CompanyBankStaMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<CompanyBankSta> getAllCompanyBankStaByDate(long companyId, String date) {
		List<CompanyBankSta> list = null;
		try {
			String sql = "select * from company_bank_sta where date like'%"+date+"%' and company_id=?";
			list = jdbcTemplate.query(sql, new Object[]{companyId},
					new RowMapperResultSetExtractor<CompanyBankSta>(
							new CompanyBankStaMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<CompanyBankSta> getAllCompanyBankStaByIdentifyStatus(
			long companyId, int status) {
		List<CompanyBankSta> list = null;
		try {
			String sql = "select * from company_bank_sta where identify_status =? and company_id=?";
			list = jdbcTemplate.query(sql, new Object[]{status, companyId},
					new RowMapperResultSetExtractor<CompanyBankSta>(
							new CompanyBankStaMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

}
