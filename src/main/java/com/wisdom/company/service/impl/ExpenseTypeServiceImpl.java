package com.wisdom.company.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wisdom.common.model.ExpenseType;
import com.wisdom.company.dao.IExpenseTypeDao;
import com.wisdom.company.service.IExpenseTypeService;

public class ExpenseTypeServiceImpl implements IExpenseTypeService {

	@Autowired
	private IExpenseTypeDao expenseTypeDao;
	
	@Override
	public Map<Long, String> getExpenseTypeMap() {
		Map<Long, String> retMap = new HashMap<>();
		List<ExpenseType> list =  expenseTypeDao.getAllExpenseType();
		for(ExpenseType type : list) {
			retMap.put(type.getId(), type.getName());
		}
		return retMap;
	}

	@Override
	public String getExpenseTypeNameById(long id) {
		ExpenseType type = expenseTypeDao.getExpenseTypeCompanyId(id);
		return type != null ? type.getName() : "";
	}

}
