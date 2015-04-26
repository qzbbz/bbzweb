package com.wisdom.accounter.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.wisdom.accounter.dao.IAccounterDao;
import com.wisdom.accounter.mapper.AccounterMapper;
import com.wisdom.common.model.Accounter;

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
		String sql = "insert into accounter (user_id, name, area_id, image, certificate_id, industry_id, career_id, comapny_id, create_time)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, accounter.getUSerId(),
				accounter.getName(), accounter.getAreaId(),
				accounter.getImage(), accounter.getCertificateId(),
				accounter.getIndustryId(), accounter.getCareerId(),
				accounter.getCreateTime());
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
		String sql = "update accounter set name=?, area_id=?, image=?, certificate_id=?, industry_id=?, career_id=?, company_id=?  where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, accounter.getName(),
				accounter.getAreaId(), accounter.getImage(),
				accounter.getCertificateId(), accounter.getIndustryId(),
				accounter.getCareerId(), accounter.getCreateTime(),
				accounter.getUSerId());
		logger.debug("updateAccounter result : {}", affectedRows);
		return affectedRows != 0;
	}

}
