package com.wisdom.user.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	public Menu getMenuById(long id) {
		String sql = "select * from menu where id = ?";
		Menu menu = null;
		try {
			menu = jdbcTemplate.queryForObject(sql, new Object[] { id },
					new MenuMapper());
			logger.debug("menu : {}", menu.toString());
		} catch (Exception e) {
			logger.debug("result size is 0.");
		}
		return menu;
	}

}