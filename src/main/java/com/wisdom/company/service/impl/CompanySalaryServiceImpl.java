package com.wisdom.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CompanySalary;
import com.wisdom.company.dao.ICompanySalaryDao;
import com.wisdom.company.service.ICompanySalaryService;

@Service("companySalaryService")
public class CompanySalaryServiceImpl implements ICompanySalaryService {

	@Autowired
	private ICompanySalaryDao companySalaryDao;
	
	@Override
	public String getCompanySalaryFileByCompanyId(long companyId) {
		return companySalaryDao.getCompanySalaryFileByCompanyId(companyId);
	}

	@Override
	public long addCompanySalary(CompanySalary companySalary) {
		return companySalaryDao.addCompanySalary(companySalary);
	}

	@Override
	public boolean deleteCompanySalaryByCompanyId(long companyId) {
		return companySalaryDao.deleteCompanySalaryByCompanyId(companyId);
	}

	@Override
	public List<CompanySalary> getAllCompanySalary(long companyId) {
		return companySalaryDao.getAllCompanySalary(companyId);
	}

}