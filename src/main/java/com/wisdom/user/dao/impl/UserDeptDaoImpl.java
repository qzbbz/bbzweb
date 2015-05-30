package com.wisdom.user.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

	@Override
	public List<UserDept> getUserDeptListByDeptId(long deptId) {
		List<UserDept> list = null;
		try {
			String sql = "select * from user_dept where dept_id =?";
			list = jdbcTemplate.query(sql, new Object[] { deptId },
					new RowMapperResultSetExtractor<UserDept>(
							new UserDeptMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<UserDept> getUserDeptListByStatusAndDeptId(int status,
			long deptId) {
		List<UserDept> list = null;
		try {
			String sql = "select * from user_dept where dept_id =? and status=?";
			list = jdbcTemplate.query(sql, new Object[] { deptId, status },
					new RowMapperResultSetExtractor<UserDept>(
							new UserDeptMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public long getUserNumByDeptId(long deptId) {
		try {
			String sql = "select count(id) from user_dept where dept_id =? ";
			long num = jdbcTemplate.queryForLong(sql, new Object[] { deptId });
			return num;
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return 0;
	}

	@Override
	public int delUserByDeptId(long deptId) {
		try{
			String sql= "delete from user_dept where dept_id=?";
			int num = jdbcTemplate.update(sql,new Object[]{deptId});
			return num;
		}catch(Exception e){
			logger.error(e.toString());
		}
		return 0;
	}

	@Override
	public boolean addUserDeptRecord(UserDept userDept) {
		if(null == userDept || null == userDept.getDeptId() || StringUtils.isEmpty(userDept.getUserId())){
			logger.error("input param error");
			return false;
		}
		
		try{
			String sql= "insert into user_dept(user_id,dept_id,status) values (?,?,?)";
			int num = jdbcTemplate.update(sql,
					userDept.getUserId(),
					userDept.getDeptId(),
					userDept.getStatus());
			return num > 0;
		}catch(Exception e){
			logger.error(e.toString());
		}
		
		return false;
	}
	
	@Override
	public boolean delUserDept(String userId,long deptId){
		if(-1 == deptId || StringUtils.isEmpty(userId)){
			logger.error("input param error");
			return false;
		}
		try{
			String sql= "delete from user_dept where user_id =? and dept_id=?";
			int num = jdbcTemplate.update(sql,
					userId,
					deptId);
			return num > 0;
		}catch(Exception e){
			logger.error(e.toString());
		}
		return false;
	}

	@Override
	public List<UserDept> getUserDeptListByDeptIdAndUserId(long deptId,
			String userId) {
		List<UserDept> list = null;
		try {
			String sql = "select * from user_dept where dept_id =? and user_id=?";
			list = jdbcTemplate.query(sql, new Object[] { deptId, userId },
					new RowMapperResultSetExtractor<UserDept>(
							new UserDeptMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean updateUserDeptStatus(String userId, int status) {
		String sql = "update user_dept set status=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, status, userId);
		logger.debug("updateUserDeptStatus result : {}", affectedRows);
		return affectedRows != 0;
	}

}