package com.wisdom.invoice.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.accounter.mapper.AccounterCareerMapper;
import com.wisdom.common.model.AccounterCareer;
import com.wisdom.common.model.InvoiceApproval;
import com.wisdom.invoice.dao.IInvoiceApprovalDao;
import com.wisdom.invoice.mapper.InvoiceApprovalMapper;
import com.wisdom.invoice.mapper.UserInvoiceMapper;

@Repository("invoiceApprovalDao")
public class InvoiceApprovalDaoImpl implements IInvoiceApprovalDao {

	private static final Logger logger = LoggerFactory
			.getLogger(InvoiceApprovalDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<InvoiceApproval> getInvoiceApprovalListByUserId(String userId){
		String sql = "select * from invoice_approval where userId = ?";
		try {
			List<InvoiceApproval> retList = jdbcTemplate.query(sql,
					new Object[] { userId }, new RowMapperResultSetExtractor<InvoiceApproval>(new InvoiceApprovalMapper()));
			logger.debug("getInvoiceApprovalListByUserId");
			return retList;
		} catch (Exception e) {
			logger.error("getInvoiceApprovalListByUserId error,"
					+ e.getMessage());
		}
		return null;
	}

	@Override
	public InvoiceApproval getInvoiceApprovalByInvoiceId(long id) {
		String sql = "select * from invoice_approval where invoice_id = ?";
		try {
			InvoiceApproval invoiceApproval = jdbcTemplate.queryForObject(sql,
					new Object[] { id }, new InvoiceApprovalMapper());
			logger.debug("getInvoiceApprovalByInvoiceId");
			return invoiceApproval;
		} catch (Exception e) {
			logger.error("getInvoiceApprovalByInvoiceId error,"
					+ e.getMessage());
		}
		return null;
	}

	@Override
	public boolean addInvoiceApproval(InvoiceApproval invoiceApproval) {
		String sql = "insert into invoice_approval (invoice_id, user_id, status, update_time, create_time)"
				+ " values (?, ?, ?, ?, ?)";

		try {
			int affectedRows = jdbcTemplate.update(
					sql,
					invoiceApproval.getInvoiceId(),
					invoiceApproval.getUserId(),
					invoiceApproval.getStatus(),
					invoiceApproval.getUpdateTime() == null ? new Timestamp(
							System.currentTimeMillis()) : invoiceApproval
							.getUpdateTime(),
					invoiceApproval.getCreateTime() == null ? new Timestamp(
							System.currentTimeMillis()) : invoiceApproval
							.getCreateTime());
			logger.debug("addInvoiceApproval result : {}", affectedRows);
			return affectedRows != 0;
		} catch (Exception e) {
			logger.error("addInvoiceApproval error." + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteInvoiceApproval(InvoiceApproval invoiceApproval) {
		String sql = "delete from invoice_approval where invoice_id = ?";
		try {
			int affectedRows = jdbcTemplate
					.update(sql, invoiceApproval.getId());
			logger.debug("deleteInvoiceApproval result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			logger.error("deleteInvoiceApproval error!" + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateInvoiceApproval(InvoiceApproval invoiceApproval) {
		String sql = "update invoice_approval set status=?, approval_status=?,update_time=? where invoice_id=?";
		try {
			int affectedRows = jdbcTemplate.update(sql,
					invoiceApproval.getStatus(),
					invoiceApproval.getApprovalStatus() == null? 0:invoiceApproval.getApprovalStatus(),
					invoiceApproval.getUpdateTime() == null ? new Timestamp(
							System.currentTimeMillis()) : invoiceApproval
							.getUpdateTime(),
					invoiceApproval.getInvoiceId());
			logger.debug("updateInvoiceApproval result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			logger.error("updateInvoiceApproval error." + e.getMessage());
		}
		return false;
	}

}