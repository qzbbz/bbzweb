package com.wisdom.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Menu;
import com.wisdom.common.model.User;
import com.wisdom.common.model.UserTypeMenu;
import com.wisdom.user.dao.IMenuDao;
import com.wisdom.user.dao.IUserQueryDao;
import com.wisdom.user.dao.IUserTypeMenuDao;
import com.wisdom.user.service.IUserMenuService;

@Service("userMenuService")
public class UserMenuServiceImpl implements IUserMenuService {

	@Autowired
	private IMenuDao menuDao;

	@Autowired
	private IUserTypeMenuDao userTypeMenuDao;

	@Autowired
	private IUserQueryDao userQueryDao;

	@Override
	public Map<String, String> getMenuAndUrlByUserId(String userId) {
		Map<String, String> menuUrlMap = new HashMap<>();
		User user = userQueryDao.getUserByUserId(userId);
		if (user != null) {
			List<UserTypeMenu> userTypeMenuList = userTypeMenuDao
					.getUserTypeMenuByUserTypeId(user.getTypeId());
			if(userTypeMenuList != null && userTypeMenuList.size() > 0) {
				for(UserTypeMenu userTypeMenu : userTypeMenuList ) {
					Menu menu = menuDao.getMenuById(userTypeMenu.getMenuId());
					menuUrlMap.put(menu.getName(), menu.getUrl());
				}
			}
		}
		return null;
	}

}
