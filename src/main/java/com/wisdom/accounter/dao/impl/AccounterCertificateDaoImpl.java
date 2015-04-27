package com.wisdom.accounter.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.accounter.dao.IAccounterCertificateDao;
import com.wisdom.accounter.mapper.AccounterCertificateMapper;
import com.wisdom.common.model.AccounterCertificate;

@Repository("accounterCertificateDao")
public class AccounterCertificateDaoImpl implements IAccounterCertificateDao {

	private static final Logger logger = LoggerFactory
			.getLogger(AccounterCertificateDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public AccounterCertificate getAccounterCertificateById(long id) {
		String sql = "select * from accounter_certificate where id = ?";
		AccounterCertificate user = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new AccounterCertificateMapper());
		return user;
	}

	@Override
	public List<AccounterCertificate> getAllAccounterCertificate() {
		List<AccounterCertificate> list = null;
		try {
			String sql = "select * from accounter_certificate";
			list = jdbcTemplate.query(sql, 
					new RowMapperResultSetExtractor<AccounterCertificate>(
							new AccounterCertificateMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean addAccounterCertificate(
			AccounterCertificate accounterCertificate) {
		String sql = "insert into accounter_certificate (name, create_time)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, accounterCertificate.getName(),
				accounterCertificate.getCreateTime());
		logger.debug("addAccounterCertificate result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteAccounterCertificate(
			AccounterCertificate accounterCertificate) {
		String sql = "delete from accounter_certificate where id = ?";
		int affectedRows = jdbcTemplate.update(sql, accounterCertificate.getId());
		logger.debug("deleteAccounterCertificate result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateAccounterCertificate(
			AccounterCertificate accounterCertificate) {
		String sql = "update accounter_cartificate set name=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, accounterCertificate.getName(),
				accounterCertificate.getId());
		logger.debug("updateAccounterCertificate result : {}", affectedRows);
		return affectedRows != 0;
	}

}
