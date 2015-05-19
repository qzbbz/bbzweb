package com.wisdom.company.service;

import com.wisdom.common.model.CompanySalary;

public interface ICompanySalaryService {

	public String getCompanySalaryFileByCompanyId(long companyId);
	
	public long addCompanySalary(CompanySalary companySalary);
	
	public boolean deleteCompanySalaryByCompanyId(long companyId);
	
}
