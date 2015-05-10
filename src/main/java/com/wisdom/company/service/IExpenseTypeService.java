package com.wisdom.company.service;

import java.util.Map;

public interface IExpenseTypeService {
	
	public Map<Long, String> getExpenseTypeMap();
	
	public String getExpenseTypeNameById(long id);
	
}