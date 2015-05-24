package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.Dept;

public interface IDeptDao {

	public Dept getDeptByDeptId(long id);
	
	public boolean addDept(Dept dept);
	
	public boolean deleteDept(Dept dept);
	
	public boolean updateDept(Dept dept);
	
	public List<Dept> getDeptListByCompanyId(long companyId);
	
}
