package com.wisdom.invoice.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.UserInvoice;
import com.wisdom.invoice.dao.IUserInvoiceDao;
import com.wisdom.invoice.mapper.UserInvoiceMapper;

@Repository("userInvoiceDao")
public class UserInvoiceDaoImpl implements IUserInvoiceDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserInvoiceDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<UserInvoice> getUserInvoiceByUserId(String userId) {
		String sql = "select * from user_invoice where user_id = ?";
		try {
			List<UserInvoice> userInvoiceList = jdbcTemplate.queryForList(sql,
					new Object[] { userId }, UserInvoice.class);
			logger.debug("getUserInvoiceByUserId");
			return userInvoiceList;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserInvoice getUserInvoiceByInvoiceId(long invoiceId) {
		String sql = "select * from user_invoice where invoice_id = ?";
		try {
			UserInvoice userInvoice = jdbcTemplate.queryForObject(sql,
					new Object[] { invoiceId }, new UserInvoiceMapper());
			return userInvoice;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addUserInvoice(UserInvoice userInvoice) {
		String sql = "insert into user_invoice (user_id, invoice_id, status, update_time, create_time)"
				+ " values (?, ?, ?, ?, ?)";
		try {
			int affectedRows = jdbcTemplate
					.update(sql,
							userInvoice.getUserId(),
							userInvoice.getInvoiceId(),
							userInvoice.getStatus() == null ? 0 : userInvoice
									.getStatus(),
							userInvoice.getUpdateTime() == null ? new Timestamp(
									System.currentTimeMillis()) : userInvoice
									.getUpdateTime(),
							userInvoice.getCreateTime() == null ? new Timestamp(
									System.currentTimeMillis()) : userInvoice
									.getCreateTime());
			logger.debug("addUserInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUserInvoice(UserInvoice userInvoice) {
		String sql = "delete from user_invoice where id = ?";
		try {
			int affectedRows = jdbcTemplate.update(sql, userInvoice.getId());
			logger.debug("deleteUserInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUserInvoice(UserInvoice userInvoice) {
		String sql = "update user_invoice set status=?, update_time=? where id=?";
		try {
			int affectedRows = jdbcTemplate
					.update(sql,
							userInvoice.getStatus() == null ? 0 : userInvoice
									.getStatus(),
							userInvoice.getUpdateTime() == null ? new Timestamp(
									System.currentTimeMillis()) : userInvoice
									.getUpdateTime(), userInvoice.getId());
			logger.debug("updateUserInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

}