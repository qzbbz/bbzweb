package com.wisdom.user.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.UserDept;
import com.wisdom.user.dao.IUserDeptDao;
import com.wisdom.user.mapper.UserDeptMapper;

@Repository("userDeptDao")
public class UserDeptDaoImpl implements IUserDeptDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDeptDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserDept getUserDeptByUserId(String userId) {
		String sql = "select * from user_dept where user_id = ?";
		UserDept userDept = null;
		try {
			userDept = jdbcTemplate.queryForObject(sql,
					new Object[] { userId }, new UserDeptMapper());
		} catch (Exception e) {
			logger.debug("result size is 0.");
		}
		return userDept;
	}

}