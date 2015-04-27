package com.wisdom.user.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.UserTypeMenu;
import com.wisdom.user.dao.IUserTypeMenuDao;
import com.wisdom.user.mapper.UserTypeMenuMapper;

@Repository("userTypeMenuDao")
public class UserTypeMenuDaoImpl implements IUserTypeMenuDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserTypeMenuDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<UserTypeMenu> getUserTypeMenuByUserTypeId(long id) {
		List<UserTypeMenu> list = null;
		try {
			String sql = "select * from user_type_menu where user_type_id =?";
			list = jdbcTemplate.query(sql, new Object[] { id },
					new RowMapperResultSetExtractor<UserTypeMenu>(
							new UserTypeMenuMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

}
