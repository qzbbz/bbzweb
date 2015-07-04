package com.wisdom.company.service;

import java.util.List;

import com.wisdom.common.model.CompanySalary;

public interface ICompanySalaryService {

	public String getCompanySalaryFileByCompanyId(long companyId);
	
	public long addCompanySalary(CompanySalary companySalary);
	
	public boolean deleteCompanySalaryByCompanyId(long companyId);
	
	public boolean deleteCompanySalaryById(long id);
	
	public List<CompanySalary> getAllCompanySalary(long companyId);
	
	public CompanySalary getCompanySalaryById(long id);
	
}
