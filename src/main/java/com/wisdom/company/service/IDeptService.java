package com.wisdom.company.service;

import java.util.List;

import com.wisdom.common.model.Dept;

public interface IDeptService {

	public String getDeptNameById(long id);
	
	public long getParentDeptIdById(long id);
	
	public String getCostCenterCodeById(long id);
	
	public List<Dept> getDeptListByCompanyId(long companyId);
	
	public long addDeptAndGetKey(final Dept dept);
	
	public boolean updateDeptInfoById(Dept dept);
	
	public long querySubDeptNumByIdAndCompanyId(long companyId, long id);
	
	public boolean deleteDept(long deptId);
	
}
