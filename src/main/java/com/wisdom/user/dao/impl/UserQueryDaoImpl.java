package com.wisdom.user.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wisdom.common.model.User;
import com.wisdom.common.model.UserDept;
import com.wisdom.common.model.UserInvitecode;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.common.model.UserPhone;
import com.wisdom.common.model.UserPhoneType;
import com.wisdom.common.model.UserPwd;
import com.wisdom.common.model.UserRole;
import com.wisdom.common.model.UserType;
import com.wisdom.user.dao.IUserQueryDao;
import com.wisdom.user.mapper.UserDeptMapper;
import com.wisdom.user.mapper.UserInviteCodeMapper;
import com.wisdom.user.mapper.UserMapper;
import com.wisdom.user.mapper.UserOpenidMapper;
import com.wisdom.user.mapper.UserPhoneMapper;
import com.wisdom.user.mapper.UserPhoneTypeMapper;
import com.wisdom.user.mapper.UserPwdMapper;
import com.wisdom.user.mapper.UserRoleMapper;
import com.wisdom.user.mapper.UserTypeMapper;

public class UserQueryDaoImpl implements IUserQueryDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserQueryDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User getUserByUserId(String userId) {
		String sql = "select * from user where user_id = ?";
		User user = jdbcTemplate.queryForObject(sql, new Object[] { userId },
				new UserMapper());
		logger.debug("getUserByUserId");
		return user;
	}

	@Override
	public UserPwd getUserPwdByUserId(String userId) {
		String sql = "select * from user_pwd where user_id = ?";
		UserPwd userPwd = jdbcTemplate.queryForObject(sql,
				new Object[] { userId }, new UserPwdMapper());
		return userPwd;
	}

	@Override
	public UserInvitecode getUserInvitecodeByInviteCode(String inviteCode) {
		String sql = "select * from user_invitecode where invite_code = ?";
		UserInvitecode userInvitecode = jdbcTemplate.queryForObject(sql,
				new Object[] { inviteCode }, new UserInviteCodeMapper());
		return userInvitecode;
	}

	@Override
	public UserType getUserTypeByUserId(String userId) {
		String sql = "select * from user_type where user_id = ?";
		UserType userType = jdbcTemplate.queryForObject(sql,
				new Object[] { userId }, new UserTypeMapper());
		return userType;
	}

	@Override
	public UserPhone getUserPhoneByUserId(String userId) {
		String sql = "select * from user_phone where user_id = ?";
		UserPhone userPhone = jdbcTemplate.queryForObject(sql,
				new Object[] { userId }, new UserPhoneMapper());
		return userPhone;
	}

	@Override
	public UserOpenid getUserOpenidByOpenid(String openid) {
		String sql = "select * from user_openid where openid = ?";
		UserOpenid userOpenid = jdbcTemplate.queryForObject(sql,
				new Object[] { openid }, new UserOpenidMapper());
		return userOpenid;
	}

	@Override
	public UserDept getUserDeptByUserId(String userId) {
		String sql = "select * from user_dept where user_id = ?";
		UserDept userDept = jdbcTemplate.queryForObject(sql,
				new Object[] { userId }, new UserDeptMapper());
		return userDept;
	}

	@Override
	public UserDept getUserDeptByDeptId(long deptId) {
		String sql = "select * from user_dept where id = ?";
		UserDept userDept = jdbcTemplate.queryForObject(sql,
				new Object[] { deptId }, new UserDeptMapper());
		return userDept;
	}

	@Override
	public UserPhoneType getUserPhoneTypeById(long id) {
		String sql = "select * from user_phone_type where id = ?";
		UserPhoneType userPhoneType = jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new UserPhoneTypeMapper());
		return userPhoneType;
	}

	@Override
	public UserRole getUserRole(String userId) {
		String sql = "select * from user_role where user_id = ?";
		UserRole userRole = jdbcTemplate.queryForObject(sql,
				new Object[] { userId }, new UserRoleMapper());
		return userRole;
	}

}
