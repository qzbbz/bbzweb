package com.wisdom.user.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Menu;
import com.wisdom.user.dao.IMenuDao;
import com.wisdom.user.mapper.MenuMapper;

@Repository("menuDao")
public class MenuDaoImpl implements IMenuDao {

	private static final Logger logger = LoggerFactory
			.getLogger(MenuDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Menu> getMenuById(long id) {
		List<Menu> list = null;
		try {
			String sql = "select * from menu where id =?";
			list = jdbcTemplate.query(sql, new Object[] { id },
					new RowMapperResultSetExtractor<Menu>(
							new MenuMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

}
