package com.wisdom.company.dao;

import com.wisdom.common.model.CompanySalary;

public interface ICompanySalaryDao {
	
	public String getCompanySalaryFileByCompanyId(long companyId);
	
	public long addCompanySalary(CompanySalary companySalary);
	
	public boolean deleteCompanySalaryByCompanyId(long companyId);
	
}
