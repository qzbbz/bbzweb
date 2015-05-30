package com.wisdom.user.dao.impl;

import com.wisdom.common.model.UserInviteCode;
import com.wisdom.user.dao.IUserInviteCodeDao;
import com.wisdom.user.mapper.UserInviteCodeMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * Created by kcchai on 2015/4/30.
 */
@Repository("userInviteCodeDao")
public class UserInviteCodeDaoImpl implements IUserInviteCodeDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserInviteCodeDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserInviteCode getUserInviteCodeByInviteCode(String inviteCode) {
		String sql = "select * from user_invitecode where invite_code = ?";
		UserInviteCode userInviteCode = null;
		try {
			userInviteCode = jdbcTemplate.queryForObject(sql,
					new Object[] { inviteCode }, new UserInviteCodeMapper());
		} catch (Exception e) {
			logger.debug("result size is 0.");
		}
		return userInviteCode;
	}

	@Override
	public boolean addUserInviteCode(String userId, String inviteCode) {
		String sql = "insert into user_invitecode (user_id, invite_code, create_time)"
				+ " values (?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, userId, inviteCode,
				new Timestamp(System.currentTimeMillis()));
		logger.debug("addUserInviteCode result : {}", affectedRows);
		return affectedRows != 0;
	}
}
