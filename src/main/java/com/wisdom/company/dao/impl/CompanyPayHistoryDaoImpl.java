package com.wisdom.company.dao.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.CompanyPayHistory;
import com.wisdom.company.dao.ICompanyPayDao;
import com.wisdom.company.dao.ICompanyPayHistoryDao;

@Repository("companyPayHistoryDao")
public class CompanyPayHistoryDaoImpl implements ICompanyPayHistoryDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyPayHistoryDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public long addCompanyHistoryPay(CompanyPayHistory companyPayHistory) {
		String sql = "insert into company_pay_history (company_id, pay_amount, service_time, order_no, mail_address, apply_invoice, contract_file, created_time, pay_status)"
				+ " values (?, ?, ?, ?, ?, ?,?,?,?)";
		int affectedRows = jdbcTemplate.update(sql, 
				companyPayHistory.getCompanyId() == null ? 0 : companyPayHistory.getCompanyId(),
				companyPayHistory.getPayAmount() == null ? "" : companyPayHistory.getPayAmount(),
				companyPayHistory.getServiceTime() == null ? "" : companyPayHistory.getServiceTime(),
				companyPayHistory.getOrderNo() == null ? "" : companyPayHistory.getOrderNo(),
				companyPayHistory.getMailAddress() == null ? "" : companyPayHistory.getMailAddress(),
				companyPayHistory.getApplyInvoice() == null ? 0 : companyPayHistory.getApplyInvoice(),
				companyPayHistory.getContractFile() == null ? "" : companyPayHistory.getContractFile(),
				companyPayHistory.getCreatedTime() == null ? new Timestamp(System.currentTimeMillis()) : companyPayHistory.getCreatedTime(),
				companyPayHistory.getPayStatus() == null ? -1 : companyPayHistory.getPayStatus());
		logger.debug("addCompanyPayHistory result : {}", affectedRows);
		return affectedRows;
	}
	

}
