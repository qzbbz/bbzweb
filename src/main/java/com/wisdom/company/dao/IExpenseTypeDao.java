package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.ExpenseType;

public interface IExpenseTypeDao {

	public List<ExpenseType> getAllExpenseType();
	
	public ExpenseType getExpenseTypeCompanyId(long expenseTypeId);
	
	public boolean addExpenseType(ExpenseType expenseType);
	
	public boolean deleteExpenseType(ExpenseType expenseType);
	
	public boolean updateExpenseType(ExpenseType expenseType);
	
	public boolean increaseExpenseTypeHit(String name);
	
}
