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
import com.wisdom.common.model.UserRole;
import com.wisdom.user.dao.IUserOperationDao;

public class UserOperationDaoImpl implements IUserOperationDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserOperationDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean addUser(User user) {
		String sql = "insert into user (user_id, type_id, company_id, create_time)"
				+ " values (?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, user.getUserId(),
				user.getTypeId(), user.getCompanyId(), user.getCreateTime());
		logger.debug("addUser result : {}", affectedRows);
		return affectedRows != 0;

	}

	@Override
	public boolean deleteUser(User user) {
		String sql = "delete from user where user_id = ?";
		int affectedRows = jdbcTemplate.update(sql, user.getUserId());
		logger.debug("deleteUser result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateUser(User user) {
		String sql = "update user set type_id=?, company_id=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, user.getTypeId(),
				user.getCompanyId(), user.getUserId());
		logger.debug("updateUser result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean addInviteCode(UserInvitecode userInvitecode) {
		String sql = "insert into user_invitecode (user_id, invite_code, create_time)"
				+ " values (?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, userInvitecode.getUserId(),
				userInvitecode.getInviteCode(), userInvitecode.getCreateTime());
		logger.debug("addInviteCode result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteInviteCode(UserInvitecode userInvitecode) {
		String sql = "delete from user_invitecode where user_id = ?";
		int affectedRows = jdbcTemplate.update(sql, userInvitecode.getUserId());
		logger.debug("deleteInviteCode result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateInviteCode(UserInvitecode userInviteCode) {
		String sql = "update user_invitecode set invite_code=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql,
				userInviteCode.getInviteCode(), userInviteCode.getUserId());
		logger.debug("updateInviteCode result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean addOpenid(UserOpenid userOpenid) {
		String sql = "insert into user_openid (user_id, openid, create_time)"
				+ " values (?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, userOpenid.getUserId(),
				userOpenid.getOpenid(), userOpenid.getCreateTime());
		logger.debug("addOpenid result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteOpenid(UserOpenid userOpenid) {
		String sql = "delete from user_openid where user_id = ?";
		int affectedRows = jdbcTemplate.update(sql, userOpenid.getUserId());
		logger.debug("deleteOpenid result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateOpenid(UserOpenid userOpenid) {
		String sql = "update user_openid set openid=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, userOpenid.getOpenid(),
				userOpenid.getUserId());
		logger.debug("updateOpenid result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean addUserPhone(UserPhone userPhone) {
		String sql = "insert into user_phone (user_id, phone, type, create_time)"
				+ " values (?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, userPhone.getUserId(),
				userPhone.getClass(), userPhone.getType(),
				userPhone.getCreateTime());
		logger.debug("addUserPhone result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteUserPhone(UserPhone userPhone) {
		String sql = "delete from user_phone where user_id = ?";
		int affectedRows = jdbcTemplate.update(sql, userPhone.getUserId());
		logger.debug("deleteUserPhone result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateUserPhone(UserPhone userPhone) {
		String sql = "update user_phone set phone=?, type=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, userPhone.getPhone(),
				userPhone.getType(), userPhone.getUserId());
		logger.debug("updateUserPhone result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean addUserDept(UserDept userDept) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserDept(UserDept userDept) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserDept(UserDept userDept) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUserPhoneType(UserPhoneType userPhoneType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserPhoneType(UserPhoneType userPhoneType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserPhoneType(UserPhoneType userPhoneType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return false;
	}

}
