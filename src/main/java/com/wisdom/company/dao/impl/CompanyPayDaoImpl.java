package com.wisdom.company.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.CompanyAndPayModel;
import com.wisdom.common.model.CompanyBankSta;
import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanyPay;
import com.wisdom.common.model.Recommender;
import com.wisdom.company.dao.ICompanyBillDao;
import com.wisdom.company.dao.ICompanyPayDao;
import com.wisdom.company.mapper.CompanyAndPayModelMapper;
import com.wisdom.company.mapper.CompanyBankStaMapper;
import com.wisdom.company.mapper.CompanyBillMapper;
import com.wisdom.company.mapper.CompanyPayMapper;
import com.wisdom.recommender.mapper.RecommenderMapper;

@Repository("companyPayDao")
public class CompanyPayDaoImpl implements ICompanyPayDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyPayDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CompanyPay getCompanyPayByCompanyIdAndPayStatus(long companyId, int status) {
		String sql = "select * from company_pay where company_id = ? and pay_status = ?";
		CompanyPay companyPay = null;
		try {
			companyPay = jdbcTemplate.queryForObject(sql,
				new Object[] { companyId, status }, new CompanyPayMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}", e.toString());
		}
		return companyPay;
	}

	@Override
	public long addCompanyPay(CompanyPay companyPay) {
		String sql = "insert into company_pay (company_id, pay_status, pay_amount, service_time, order_no, create_time)"
				+ " values (?, ?, ?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, 
				companyPay.getCompanyId() == null ? 0 : companyPay.getCompanyId(),
				companyPay.getPayStatus() == null ? 0 : companyPay.getPayStatus(),
				companyPay.getPayAmount() == null ? "" : companyPay.getPayAmount(),
				companyPay.getServiceTime() == null ? "" : companyPay.getServiceTime(),
				companyPay.getOrderNo() == null ? "" : companyPay.getOrderNo(),
				companyPay.getCreateTime() == null ? new Timestamp(System.currentTimeMillis()) : companyPay.getCreateTime());
		logger.debug("addCompanyPay result : {}", affectedRows);
		return affectedRows;
	}

	@Override
	public boolean deleteCompanyPayByCompanyId(long companyId) {
		String sql = "delete from company_pay where company_id = ?";
		int affectedRows = jdbcTemplate.update(sql, companyId);
		logger.debug("deleteCompanyPayByCompanyId result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyPayPayStatusByCompanyId(long companyId, int status) {
		String sql = "update company_pay set pay_status=? where company_id=?";
		int affectedRows = jdbcTemplate.update(sql, status, companyId);
		logger.debug("updateCompanyPay result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyOrderNoByCompanyId(long companyId, String orderNo) {
		String sql = "update company_pay set order_no=? where company_id=?";
		int affectedRows = jdbcTemplate.update(sql, orderNo, companyId);
		logger.debug("updateCompanyPay result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyPayStatusAndTimeByOrderNo(String orderNo, int status, Timestamp time, String contractFile) {
		String sql = "update company_pay set pay_status=?, create_time=?, contract_file=? where order_no=?";
		int affectedRows = jdbcTemplate.update(sql, status, time, contractFile, orderNo);
		logger.debug("updateCompanyPay result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public CompanyPay getCompanyPayByCompanyId(long companyId) {
		String sql = "select * from company_pay where company_id = ?";
		CompanyPay companyPay = null;
		try {
			companyPay = jdbcTemplate.queryForObject(sql,
				new Object[] { companyId }, new CompanyPayMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}", e.toString());
		}
		return companyPay;
	}

	@Override
	public boolean updateCompanyPayByCompanyId(Long companyId, Integer payStatus, Double amount, String orderNo, int serviceTime) {
		String sql = "update company_pay set pay_status=?, pay_amount=?, service_time=?, order_no=?, create_time=NOW() where company_id=?";
		int affectedRows = jdbcTemplate.update(sql, payStatus, amount, serviceTime, orderNo, companyId);
		logger.debug("updateCompanyPay result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateApplyInvoiceByCompanyId(Long companyId, int applyInvoice, String address) {
		String sql = "update company_pay set apply_invoice=? , mail_address = ? where company_id=?";
		int affectedRows = jdbcTemplate.update(sql, applyInvoice, address, companyId);
		logger.debug("updateApplyInvoiceByCompanyId result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public CompanyPay getCompanyPayByOrderNo(String orderNo) {
		String sql = "select * from company_pay where order_no=?";
		CompanyPay companyPay = null;
		try {
			companyPay = jdbcTemplate.queryForObject(sql,
				new Object[] { orderNo }, new CompanyPayMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}", e.toString());
		}
		return companyPay;
	}

	@Override
	public boolean updateCompanyPayStatusToTrial(Long companyId) {
		String sql = "update company_pay set trial = 1 where company_id =?";
		try{
			int affectedRows = jdbcTemplate.update(sql, companyId);
			logger.debug("updateCompanyPay result : {}", affectedRows);
			return affectedRows != 0;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public List<CompanyPay> getExpiredCompanyPay() {
		String sql = "select * from company_pay where DATE_ADD(create_time, INTERVAL service_time MONTH)  < NOW()";
		List<CompanyPay> list = new ArrayList<>();
		try{
			list = jdbcTemplate.query(sql, 
					new RowMapperResultSetExtractor<CompanyPay>(
							new CompanyPayMapper()));	
		} catch(Exception e){
			logger.debug(e.toString());
		}

		return list;
	}

	@Override
	public List<CompanyAndPayModel> getCompanyAndPayModel() {
		String sql = "select a.id, a.name, b.pay_amount, b.service_time, b.create_time from company a left join company_pay b on a.id = b.company_id where a.parent_id = -1";
		List<CompanyAndPayModel> list = new ArrayList<>();
		try{
			list = jdbcTemplate.query(sql, 
					new RowMapperResultSetExtractor<CompanyAndPayModel>(
							new CompanyAndPayModelMapper()));	
		} catch(Exception e){
			logger.debug(e.toString());
		}

		return list;
	}

}
