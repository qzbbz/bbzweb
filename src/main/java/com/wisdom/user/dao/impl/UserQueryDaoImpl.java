package com.wisdom.user.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.User;
import com.wisdom.common.model.UserDept;
import com.wisdom.common.model.UserInviteCode;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.common.model.UserPhone;
import com.wisdom.common.model.UserPhoneType;
import com.wisdom.common.model.UserPwd;
import com.wisdom.common.model.UserRole;
import com.wisdom.common.model.UserType;
import com.wisdom.company.dao.ICompanyDao;
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

@Repository("userQueryDao")
public class UserQueryDaoImpl implements IUserQueryDao {

	private static final Logger logger = LoggerFactory.getLogger(UserQueryDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ICompanyDao companyDao;

	@Override
	public User getUserByUserId(String userId) {
		String sql = "select * from user where user_id = ?";
		User user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UserMapper());
		} catch (Exception e) {
			logger.error("resuly is 0, exception : " + e.toString());
		}
		return user;
	}

	@Override
	public UserPwd getUserPwdByUserId(String userId) {
		String sql = "select * from user_pwd where user_id = ?";
		UserPwd userPwd = null;
		try {
			userPwd = jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UserPwdMapper());
		} catch (Exception e) {
			logger.info("resuly is 0.");
		}
		return userPwd;
	}

	@Override
	public UserInviteCode getUserInvitecodeByInviteCode(String inviteCode) {
		String sql = "select * from user_invitecode where invite_code = ?";
		UserInviteCode userInvitecode = null;
		try {
			userInvitecode = jdbcTemplate.queryForObject(sql, new Object[] { inviteCode }, new UserInviteCodeMapper());
		} catch (Exception e) {
			logger.info("resuly is 0.");
		}
		return userInvitecode;
	}

	@Override
	public UserPhone getUserPhoneByUserId(String userId) {
		String sql = "select * from user_phone where user_id = ?";
		UserPhone userPhone = null;
		try {
			userPhone = jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UserPhoneMapper());
		} catch (Exception e) {
			logger.info("resuly is 0.");
		}
		return userPhone;
	}

	@Override
	public List<UserOpenid> getUserOpenidByOpenid(String openid) {
		List<UserOpenid> userOpenid = null;
		String sql = "select * from user_openid where openid = ?";
		try {
			userOpenid = jdbcTemplate.query(sql, new Object[] { openid },
					new RowMapperResultSetExtractor<UserOpenid>(new UserOpenidMapper()));
		} catch (Exception e) {
			logger.info("result is 0.");
		}
		return userOpenid;
	}

	@Override
	public UserDept getUserDeptByUserId(String userId) {
		String sql = "select * from user_dept where user_id = ?";
		UserDept userDept = null;
		try {
			userDept = jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UserDeptMapper());
		} catch (Exception e) {
			logger.info("resuly is 0.");
		}
		return userDept;
	}

	@Override
	public UserDept getUserDeptByDeptId(long deptId) {
		String sql = "select * from user_dept where id = ?";
		UserDept userDept = null;
		try {
			userDept = jdbcTemplate.queryForObject(sql, new Object[] { deptId }, new UserDeptMapper());
		} catch (Exception e) {
			logger.info("resuly is 0.");
		}
		return userDept;
	}

	@Override
	public UserPhoneType getUserPhoneTypeById(long id) {
		String sql = "select * from user_phone_type where id = ?";
		UserPhoneType userPhoneType = null;
		try {
			userPhoneType = jdbcTemplate.queryForObject(sql, new Object[] { id }, new UserPhoneTypeMapper());
		} catch (Exception e) {
			logger.info("resuly is 0.");
		}
		return userPhoneType;
	}

	@Override
	public UserRole getUserRole(String userId) {
		String sql = "select * from user_role where user_id = ?";
		UserRole userRole = null;
		try {
			userRole = jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UserRoleMapper());
		} catch (Exception e) {
			logger.info("resuly is 0.");
		}
		return userRole;
	}

	@Override
	public UserType getUserTypeById(long id) {
		String sql = "select * from user_type where id = ?";
		UserType userType = null;
		try {
			userType = jdbcTemplate.queryForObject(sql, new Object[] { id }, new UserTypeMapper());
		} catch (Exception e) {
			logger.info("resuly is 0.");
		}
		return userType;
	}

	@Override
	public UserOpenid getUserOpenidByUserId(String userId) {
		UserOpenid userOpenid = null;
		String sql = "select * from user_openid where user_id = ?";
		logger.debug("userId : {}", userId);
		try {
			userOpenid = jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UserOpenidMapper());
		} catch (Exception e) {
			logger.info("resuly is 0.");
		}
		return userOpenid;
	}

	@Override
	public List<User> getUserListByCompanyId(long companyId) {
		List<User> list = null;
		try {
			String sql = "select * from user where company_id =?";
			list = jdbcTemplate.query(sql, new Object[] { companyId },
					new RowMapperResultSetExtractor<User>(new UserMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public UserInviteCode getUserInvitecodeByUserId(String userId) {
		String sql = "select * from user_invitecode where user_id = ?";
		UserInviteCode userInvitecode = null;
		try {
			userInvitecode = jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UserInviteCodeMapper());
		} catch (Exception e) {
			logger.info("resuly is 0.");
		}
		return userInvitecode;
	}

	@Override
	public List<User> getAccounterUserList() {
		List<User> list = null;
		try {
			String sql = "select * from user where type_id ='1'";
			list = jdbcTemplate.query(sql, new RowMapperResultSetExtractor<User>(new UserMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public User getCompanyAdminUserByCompanyId(long companyId) {
		String sql = "select * from user where company_id = ? and type_id='2'";
		User user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[] { companyId }, new UserMapper());
		} catch (Exception e) {
			logger.error("result is 0, exception : " + e.toString());
		}
		while (user == null) {
			Company company = companyDao.getCompanyByCompanyId(companyId);
			company = companyDao.getCompanyByCompanyId(company.getParentId());
			if(company == null) break;
			try {
				user = jdbcTemplate.queryForObject(sql, new Object[] { company.getId() }, new UserMapper());
			} catch (Exception e) {
				logger.error("result is 0, exception : " + e.toString());
			}
		}
		return user;
	}

	@Override
	public String getAccounterUserIdByEmployeeUserId(String user_id) {
		Map<String, Object> resultMap = new HashMap<>();
		String sql = "select * from accounter_employee where employee_user_id=?";
		try {
			resultMap = jdbcTemplate.queryForMap(sql, user_id);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return resultMap.isEmpty()?"":String.valueOf(resultMap.get("accounter_user_id"));
	}
}
