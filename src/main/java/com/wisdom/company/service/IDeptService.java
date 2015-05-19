package com.wisdom.company.service;

public interface IDeptService {

	public String getDeptNameById(long id);
	
	public long getParentDeptIdById(long id);
	
	public String getCostCenterCodeById(long id);
	
}
