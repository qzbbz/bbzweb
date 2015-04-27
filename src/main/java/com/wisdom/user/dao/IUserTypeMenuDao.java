package com.wisdom.user.dao;

import java.util.List;

import com.wisdom.common.model.UserTypeMenu;

public interface IUserTypeMenuDao {

	public List<UserTypeMenu> getUserTypeMenuByUserTypeId(long id);
		
}
