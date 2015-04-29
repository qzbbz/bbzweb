package com.wisdom.company.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.Employment;
import com.wisdom.company.dao.ICompanyDao;
import com.wisdom.company.dao.IEmploymentDao;
import com.wisdom.company.service.ICompanyService;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;
	
	@Autowired
	private IEmploymentDao employmentDao;
	
	@Override
	public long addCompany(Company company) {
		return companyDao.addCompany(company);
	}

	@Override
	public int accounterAmountBelongToCompany(long companyId) {
		List<Employment> list = employmentDao.getEmploymentByCompanyId(companyId);
		return list != null ? list.size() : 0;
	}

	@Override
	public boolean companySelectAccounter(long companyId, String userId) {
		Employment emp = new Employment();
		emp.setCompanyId(companyId);
		emp.setUserId(userId);
		emp.setCreateTime(new Timestamp(System.currentTimeMillis()));
		return employmentDao.addEmployment(emp);
	}
	
}