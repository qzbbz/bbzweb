package com.wisdom.invoice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Invoice;
import com.wisdom.common.model.UserInvoice;
import com.wisdom.invoice.dao.IInvoiceDao;
import com.wisdom.invoice.mapper.InvoiceMapper;
import com.wisdom.invoice.mapper.UserInvoiceMapper;

@Repository("invoiceDao")
public class InvoiceDaoImpl implements IInvoiceDao {

	private static final Logger logger = LoggerFactory
			.getLogger(InvoiceDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Invoice getInvoiceById(long id) {
		String sql = "select * from invoice where id = ?";
		try {
			Invoice invoice = jdbcTemplate.queryForObject(sql,
					new Object[] { id }, new InvoiceMapper());
			logger.debug("getInvoiceById");
			return invoice;
		} catch (DataAccessException e) {
			logger.error("getInvoiceById error" + e.getMessage());
		}
		return null;
	}
	@Override
	public Invoice getInvoiceByIdAndStatus(long invoiceId,int status){
		String sql = "select * from invoice where id = ? and status = ?";
		try {
			Invoice invoice = jdbcTemplate.queryForObject(sql,
					new Object[] { invoiceId,status }, new InvoiceMapper());
			logger.debug("getInvoiceByIdAndStatus,invoiceId:" + invoiceId + ",status:" + status);
			return invoice;
		} catch (DataAccessException e) {
			logger.error("getInvoiceById error" + e.getMessage());
		}
		return null;
	}

	@Override
	public long addInvoiceAndGetKey(final Invoice invoice) {
		logger.debug("Enter addInvoiceAndGetKey");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String title = invoice.getTitle() == null ? "" : invoice.getTitle();
		String dateTime = invoice.getDate() == null ? 
				new Timestamp(System.currentTimeMillis()).toString() : String.valueOf(invoice.getDate());
		logger.debug("TITLE : " + title);
		logger.debug("DATE:" + dateTime);
		try {
			int id = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					String sql = "insert into invoice (expense_type_id,status,title,amount,detail_desc,date,cost_center,create_time)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement ps = connection.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, invoice.getExpenseTypeId() == null? 0: invoice.getExpenseTypeId());
					ps.setInt(2, invoice.getStatus() == null? 0: invoice.getStatus());
					ps.setString(
							3,
							invoice.getTitle() == null ? "" : invoice
									.getTitle());
					ps.setDouble(4, invoice.getAmount()==null?0:invoice.getAmount());
					ps.setString(5, invoice.getDesc() == null?"":invoice.getDesc());
					ps.setTimestamp(
							6,
							invoice.getDate() == null ? 
									new Timestamp(System.currentTimeMillis()) : invoice.getDate());
					ps.setString(7, invoice.getCostCenter() == null?"":invoice.getCostCenter());
					ps.setTimestamp(
							8,
							invoice.getCreateTime() == null ?
									new Timestamp(System.currentTimeMillis()) : invoice.getCreateTime());
					return ps;
				}
			}, keyHolder);
			logger.debug("addInvoiceAndGetKey result : {}", keyHolder.getKey().longValue());
			return keyHolder.getKey().longValue();
		} catch (DataAccessException e) {
			logger.error("addInvoiceAndGetKey error." + e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean deleteInvoice(Invoice invoice) {
		String sql = "delete from invoice where id = ?";
		try {
			int affectedRows = jdbcTemplate.update(sql, invoice.getId());
			logger.debug("deleteInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateInvoice(Invoice invoice) {
		String sql = "update invoice set title=?, amount=?, date=? ,detail_desc=? ,expense_type_id=? where id=?";
		try {
			int affectedRows = jdbcTemplate.update(
					sql,
					invoice.getTitle() == null ? "" : invoice.getTitle(),
					invoice.getAmount()==null?0:invoice.getAmount(),
					invoice.getDate() == null ? new Timestamp(System
							.currentTimeMillis()) : invoice.getDate(), 
					invoice.getDesc() == null? "" : invoice.getDesc(),
					invoice.getExpenseTypeId() == null?0 : invoice.getExpenseTypeId(), 
					invoice.getId());
					
			logger.debug("updateInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Invoice> getUserInvoiceByStatus(String userId,String status){
		String sql = "select a.id id,expense_type_id,status,title,amount,detail_desc,date " +
					" from user_invoice b, invoice a where b.user_id=? and b.invoice_id = a.id and a.status=?";
		try {
			List list = jdbcTemplate.query(sql,
					new Object[] { userId,status },new RowMapperResultSetExtractor<Invoice>(
							new InvoiceMapper()));
			logger.debug("getInvoiceById");
			return list;
		} catch (DataAccessException e) {
			logger.error("getInvoiceById error" + e.getMessage());
		}
		return null;
	
	}
	@Override
	public List<Invoice> getUserInvoiceByStatusByPage(String userId,
			String status, int begin, int end) {
		
		String sql = "select a.id id,expense_type_id,status,title,amount,detail_desc,date " +
				" from user_invoice b, invoice a where b.user_id=? and b.invoice_id = a.id and a.status=? limit ? and ?";
	try {
		List list = jdbcTemplate.query(sql,
				new Object[] { userId,status,begin,end },new RowMapperResultSetExtractor<Invoice>(
						new InvoiceMapper()));
		logger.debug("getInvoiceById");
		return list;
	} catch (DataAccessException e) {
		logger.error("getInvoiceById error" + e.getMessage());
	}
		return null;
	}
	
	@Override
	public boolean updateInvoiceStatus(long invoiceId,int status) {
		String sql = "update invoice set status=? where id=?";
		try {
			int affectedRows = jdbcTemplate.update(
					sql,invoiceId,status);
					
			logger.debug("updateInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
}
