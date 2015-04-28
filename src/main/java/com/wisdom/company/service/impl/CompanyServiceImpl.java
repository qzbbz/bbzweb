package com.wisdom.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Company;
import com.wisdom.company.dao.ICompanyDao;
import com.wisdom.company.service.ICompanyService;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;
	
	@Override
	public long addCompany(Company company) {
		return companyDao.addCompany(company);
	}
	
}