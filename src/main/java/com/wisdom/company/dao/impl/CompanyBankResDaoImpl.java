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

import com.wisdom.common.model.CompanyBankRes;
import com.wisdom.company.dao.ICompanyBankResDao;
import com.wisdom.company.mapper.CompanyBankResMapper;

@Repository("CompanyBankResDao")
public class CompanyBankResDaoImpl implements ICompanyBankResDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyBankResDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public CompanyBankRes getCompanyBankResById(long id) {
		logger.debug("companyId : {}", id);
		String sql = "select * from company_bank_res where id = ?";
		CompanyBankRes CompanyBankRes = null;
		try {
			CompanyBankRes = jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new CompanyBankResMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}", e.toString());
		}
		return CompanyBankRes;
	}

	@Override
	public long addCompanyBankRes(CompanyBankRes CompanyBankRes) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {  
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
	        	String sql = "insert into company_bank_res (company_id, date, ide_date, ide_name, ide_bank_name, ide_account, file_name, identify_status, create_time)"
	    				+ " values (?, ?, ?, ?, ?,?,?,?,?)";
	               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
	               ps.setLong(1, CompanyBankRes.getCompanyId() == null ? 0 : CompanyBankRes.getCompanyId());  
	               ps.setString(2, CompanyBankRes.getDate() == null ? "" : CompanyBankRes.getDate());
	               ps.setString(3, CompanyBankRes.getIdeDate() == null ? "" : CompanyBankRes.getIdeDate());
	               ps.setString(4, CompanyBankRes.getIdeName() == null ? "" : CompanyBankRes.getIdeName());
	               ps.setString(5, CompanyBankRes.getIdeBankName() == null ? "" : CompanyBankRes.getIdeBankName());
	               ps.setString(6, CompanyBankRes.getIdeAccount() == null ? "" : CompanyBankRes.getIdeAccount());
	               ps.setString(7, CompanyBankRes.getFileName() == null ? "" : CompanyBankRes.getFileName());
	               ps.setInt(8, CompanyBankRes.getIdentifyStatus() == null ? 0: CompanyBankRes.getIdentifyStatus());
	               ps.setTimestamp(9, CompanyBankRes.getCreateTime() == null ? new Timestamp(System.currentTimeMillis()) : CompanyBankRes.getCreateTime());
	               return ps;  
	        }  
	    }, keyHolder);
		logger.debug("addCompany result : {}", keyHolder.getKey().longValue());
		return keyHolder.getKey().longValue();
	}

	@Override
	public boolean deleteCompanyBankResById(long id) {
		String sql = "delete from company_bank_res where id = ?";
		int affectedRows = jdbcTemplate.update(sql, id);
		logger.debug("deleteCompanyBankResByCompanyId result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyBankRes(CompanyBankRes CompanyBankRes) {
		String sql = "update company_bank_res set ide_name=?, id_bank_name=?, ide_account=?, ide_date=? where id=?";
		logger.debug("updateCompanyBankRes : {}", CompanyBankRes.toString());
		int affectedRows = jdbcTemplate.update(sql,
				CompanyBankRes.getIdeName() == null ? "" : CompanyBankRes.getIdeName(),
				CompanyBankRes.getIdeBankName() == null ? "" : CompanyBankRes.getIdeBankName(),
				CompanyBankRes.getIdeAccount() == null ? "" : CompanyBankRes.getIdeAccount(),
				CompanyBankRes.getIdeDate() == null ? "" : CompanyBankRes.getIdeDate(),
				CompanyBankRes.getId());
		logger.debug("updateCompanyBankRes result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyBankResIdentifyStatusById(
			long id, int status) {
		String sql = "update company_bank_res set identify_status=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, status, id);
		logger.debug("updateCompanyBankResIdentifyStatusById result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public List<CompanyBankRes> getAllCompanyBankRes(long companyId) {
		List<CompanyBankRes> list = null;
		try {
			String sql = "select * from company_bank_res where company_id=?";
			list = jdbcTemplate.query(sql, new Object[]{companyId}, 
					new RowMapperResultSetExtractor<CompanyBankRes>(
							new CompanyBankResMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<CompanyBankRes> getAllCompanyBankResByDate(long companyId, String date) {
		List<CompanyBankRes> list = null;
		try {
			String sql = "select * from company_bank_res where date like'%"+date+"%' and company_id=?";
			list = jdbcTemplate.query(sql, new Object[]{companyId},
					new RowMapperResultSetExtractor<CompanyBankRes>(
							new CompanyBankResMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<CompanyBankRes> getAllCompanyBankResByIdentifyStatus(
			long companyId, int status) {
		List<CompanyBankRes> list = null;
		try {
			String sql = "select * from company_bank_res where identify_status =? and company_id=?";
			list = jdbcTemplate.query(sql, new Object[]{status, companyId},
					new RowMapperResultSetExtractor<CompanyBankRes>(
							new CompanyBankResMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

}
