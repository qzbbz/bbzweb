package com.wisdom.user.dao.impl;

import com.wisdom.common.model.UserOpenid;
import com.wisdom.user.dao.IUserOpenIdDao;
import com.wisdom.user.mapper.UserOpenidMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * Created by kcchai on 2015/4/30.
 */
@Repository("userOpenidDao")
public class UserOpenIdDaoImpl implements IUserOpenIdDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserOpenIdDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserOpenid getUserOpenidByUserId(String userId) {
		String sql = "select * from user_openid where user_id = ?";
		UserOpenid userOpenid = null;
		try {
			userOpenid = jdbcTemplate.queryForObject(sql,
					new Object[] { userId }, new UserOpenidMapper());
		} catch (Exception e) {
			logger.debug("result size is 0.");
		}
		return userOpenid;
	}

	@Override
	public boolean addUserOpenid(String userId, String openId) {
		String sql = "insert into user_openid (user_id, openid, create_time)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, userId, openId,
				new Timestamp(System.currentTimeMillis()));
		logger.debug("addUserOpenid result : {}", affectedRows);
		return affectedRows != 0;
	}
}
