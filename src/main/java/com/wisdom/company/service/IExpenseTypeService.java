package com.wisdom.company.service;

import java.util.List;
import java.util.Map;

public interface IExpenseTypeService {
	
	public List<Map<String, String>> getExpenseTypeMap();
	
	public String getExpenseTypeNameById(long id);
	
}