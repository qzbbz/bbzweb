package com.wisdom.user.service;

import java.util.Map;

public interface IUserMenuService {
	
	public Map<String, String> getMenuAndUrlByUserId(String userId);
	
}
