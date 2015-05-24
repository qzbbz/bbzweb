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

	@Override
	public String getCompanyName(long companyId) {
		Company company = companyDao.getCompanyByCompanyId(companyId);
		return company != null ? company.getName() : "";
	}

	@Override
	public boolean updateCompanyName(String companyName, long companyId) {
		return companyDao.updateCompanyName(companyName, companyId);
	}

	@Override
	public String getParentCompanyNameByCompanyId(long companyId) {
		Company company = companyDao.getCompanyByCompanyId(companyId);
		if(company == null) return "";
		company = companyDao.getCompanyByCompanyId(company.getParentId());
		if(company == null) return "";
		return company.getName() == null ? "" : company.getName();
	}

	@Override
	public List<Company> getSubCompanyListByCompanyId(long companyId) {
		return companyDao.getSubCompanyListByCompanyId(companyId);
	}
	
}