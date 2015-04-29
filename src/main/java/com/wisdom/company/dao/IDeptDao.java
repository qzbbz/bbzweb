package com.wisdom.company.dao;

import com.wisdom.common.model.Dept;

public interface IDeptDao {

	public Dept getDeptByDeptId(long id);
	
	public boolean addDept(Dept dept);
	
	public boolean deleteDept(Dept dept);
	
	public boolean updateDept(Dept dept);
	
}
