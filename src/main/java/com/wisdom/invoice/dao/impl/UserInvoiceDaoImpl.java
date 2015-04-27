package com.wisdom.invoice.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wisdom.common.model.UserInvoice;
import com.wisdom.invoice.dao.IUserInvoice;
import com.wisdom.invoice.mapper.UserInvoiceMapper;

public class UserInvoiceDaoImpl implements IUserInvoice {

	private static final Logger logger = LoggerFactory
			.getLogger(UserInvoiceDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public UserInvoice getUserInvoiceByUserId(String userId) {
		String sql = "select * from user_invoice where user_id = ?";
		UserInvoice userInvoice = jdbcTemplate.queryForObject(sql,
				new Object[] { userId }, new UserInvoiceMapper());
		logger.debug("getUserInvoiceByUserId");
		return userInvoice;
	}

	@Override
	public UserInvoice getUserInvoiceByInvoiceId(long invoiceId) {
		String sql = "select * from user_invoice where invoice_id = ?";
		UserInvoice userInvoice = jdbcTemplate.queryForObject(sql,
				new Object[] { invoiceId }, new UserInvoiceMapper());
		return userInvoice;
	}

	@Override
	public boolean addUserInvoice(UserInvoice userInvoice) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserInvoice(UserInvoice userInvoice) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserInvoice(UserInvoice userInvoice) {
		// TODO Auto-generated method stub
		return false;
	}	
	
}
