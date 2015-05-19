package com.wisdom.user.dao.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.User;
import com.wisdom.common.model.UserDept;
import com.wisdom.common.model.UserInviteCode;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.common.model.UserPhone;
import com.wisdom.common.model.UserPhoneType;
import com.wisdom.common.model.UserPwd;
import com.wisdom.common.model.UserRole;
import com.wisdom.user.dao.IUserOperationDao;

@Repository("userOperationDao")
public class UserOperationDaoImpl implements IUserOperationDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserOperationDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean addUser(User user) {
		String sql = "insert into user (user_name, msg_email, user_id, type_id, company_id, user_encode, create_time)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql,
				user.getUserName() == null ? "" : user.getUserName(),
				user.getMsgEmail() == null ? "" : user.getMsgEmail(),
				user.getUserId() == null ? "" : user.getUserId(),
				user.getTypeId() == null ? 0 : user.getTypeId(),
				user.getCompanyId() == null ? -1 : user.getCompanyId(),
				user.getUserEncode() == null ? "" : user.getUserEncode(),
				user.getCreateTime()==null ? new Timestamp(System.currentTimeMillis()) : user.getCreateTime());
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
		String sql = "update user set user_name=?, msg_email=?, type_id=?, company_id=?, user_encode=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql,
				user.getUserName() == null ? "" : user.getUserName(),
				user.getMsgEmail() == null ? "" : user.getMsgEmail(),
				user.getTypeId() == null ? 0 : user.getTypeId(),
				user.getCompanyId() == null ? -1 : user.getCompanyId(),
				user.getUserEncode() == null ? "" : user.getUserEncode(),
				user.getUserId() == null ? "" : user.getUserId());
		logger.debug("updateUser result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateUserMsgEmail(String email, String userId) {
		String sql = "update user set msg_email=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, email, userId);
		logger.debug("updateUserMsgEmail result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateUserNameByUserId(String userName, String userId) {
		String sql = "update user set user_name=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, userName, userId);
		logger.debug("updateUserNameByUserId result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean addInviteCode(UserInviteCode userInvitecode) {
		String sql = "insert into user_invitecode (user_id, invite_code, create_time)"
				+ " values (?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, userInvitecode.getUserId(),
				userInvitecode.getInviteCode(), userInvitecode.getCreateTime());
		logger.debug("addInviteCode result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteInviteCode(UserInviteCode userInvitecode) {
		String sql = "delete from user_invitecode where user_id = ?";
		int affectedRows = jdbcTemplate.update(sql, userInvitecode.getUserId());
		logger.debug("deleteInviteCode result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateInviteCode(UserInviteCode userInviteCode) {
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
				userPhone.getPhone(), userPhone.getType(),
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

	@Override
	public boolean updateUserRegStatus(int status, String userId) {
		String sql = "update user set user_reg_status=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, status, userId);
		logger.debug("updateUserRegStatus result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean addUserPwd(UserPwd userPwd) {
		String sql = "insert into user_pwd (user_id, pwd, create_time)"
				+ " values (?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, userPwd.getUserId(),
				userPwd.getPwd(), new Timestamp(System.currentTimeMillis()));
		logger.debug("addUserPwd result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteUserPwd(String userId) {
		String sql = "delete from user_pwd where user_id = ?";
		int affectedRows = jdbcTemplate.update(sql, userId);
		logger.debug("deleteUserPwd result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateUserPwd(UserPwd userPwd) {
		String sql = "update user_pwd set pwd=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, userPwd);
		logger.debug("updateUserPwd result : {}", affectedRows);
		return affectedRows != 0;
	}

}
