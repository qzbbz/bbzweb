package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.Employment;

public interface IEmploymentDao {

	public List<Employment> getEmploymentByCompanyId(long id);
	
	public boolean addEmployment(Employment emp);
	
	public boolean deleteEmployment(Employment emp);
	
	public boolean updateEmployment(Employment emp);
	
}
