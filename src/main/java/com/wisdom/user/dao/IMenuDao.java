package com.wisdom.user.dao;

import java.util.List;

import com.wisdom.common.model.Menu;

public interface IMenuDao {
	
	public List<Menu> getMenuById(long id);
	
}
