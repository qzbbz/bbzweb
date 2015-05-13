package com.wisdom.company.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.ExpenseType;
import com.wisdom.company.dao.IExpenseTypeDao;
import com.wisdom.company.service.IExpenseTypeService;

@Service("expenseTypeService")
public class ExpenseTypeServiceImpl implements IExpenseTypeService {

	@Autowired
	private IExpenseTypeDao expenseTypeDao;
	
	@Override
	public List<Map<String, String>> getExpenseTypeMap() {
		List<Map<String, String>> retList = new ArrayList<>();
		List<ExpenseType> list =  expenseTypeDao.getAllExpenseType();
		for(ExpenseType type : list) {
			Map<String, String> map = new HashMap<>();
			map.put("name", type.getName());
			map.put("value", String.valueOf(type.getId()));
			retList.add(map);
		}
		return retList;
	}

	@Override
	public String getExpenseTypeNameById(long id) {
		ExpenseType type = expenseTypeDao.getExpenseTypeCompanyId(id);
		return type != null ? type.getName() : "";
	}

}
