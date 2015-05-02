package com.wisdom.accounter.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.accounter.dao.IAccounterDao;
import com.wisdom.accounter.mapper.AccounterMapper;
import com.wisdom.common.model.Accounter;

@Repository("accounterDao")
public class AccounterDaoImpl implements IAccounterDao {

	private static final Logger logger = LoggerFactory
			.getLogger(AccounterDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Accounter getAccounterByUserId(String userId) {
		String sql = "select * from accounter where user_id = ?";
		Accounter user = jdbcTemplate.queryForObject(sql,
				new Object[] { userId }, new AccounterMapper());
		return user;
	}

	@Override
	public List<Accounter> getAccounterByCompanyId(long companyId) {
		List<Accounter> list = null;
		try {
			String sql = "select * from accounter where companyId=?";
			list = jdbcTemplate.query(sql, new Object[] { companyId },
					new RowMapperResultSetExtractor<Accounter>(
							new AccounterMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean addAccounter(Accounter accounter) {
		String sql = "insert into accounter (user_id, area, city, province, image, certificate, industry, career, create_time)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, accounter.getUserId(),
				accounter.getArea(), accounter.getCity(),
				accounter.getProvince(), accounter.getImage(),
				accounter.getCertificate(), accounter.getIndustry(),
				accounter.getCareer(), accounter.getCreateTime());
		logger.debug("addAccounter result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteAccounter(Accounter accounter) {
		String sql = "delete from accounter where id = ?";
		int affectedRows = jdbcTemplate.update(sql, accounter.getId());
		logger.debug("deleteAccounter result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateAccounter(Accounter accounter) {
		String sql = "update accounter set area=?, city=?, province=?, image=?, certificate=?, industry=?, career=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, 
				accounter.getArea(), accounter.getCity(),
				accounter.getProvince(), accounter.getImage(),
				accounter.getCertificate(), accounter.getIndustry(),
				accounter.getCareer(), accounter.getUserId());
		logger.debug("updateAccounter result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean isAccounterExistByUserId(String userId) {
		String sql = "select id from accounter where user_id = ?";
		Accounter user = jdbcTemplate.queryForObject(sql,
				new Object[] { userId }, new AccounterMapper());
		return user != null ? true : false;
	}

	@Override
	public List<Accounter> getAllAccounter() {
		List<Accounter> list = null;
		try {
			String sql = "select * from accounter";
			list = jdbcTemplate.query(sql,
					new RowMapperResultSetExtractor<Accounter>(
							new AccounterMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

}
