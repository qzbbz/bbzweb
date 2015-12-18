package com.wisdom.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CompanyPayHistory;
import com.wisdom.company.dao.ICompanyPayHistoryDao;
import com.wisdom.company.service.ICompanyPayHistoryService;


@Service("companyPayHistoryService")
public class CompanyPayHistoryServiceImpl implements ICompanyPayHistoryService {

	@Autowired
	private ICompanyPayHistoryDao companyPayHistoryDao;
	
	@Override
	public long addCompanyPayHistory(CompanyPayHistory companyPayHistory) {
		return companyPayHistoryDao.addCompanyHistoryPay(companyPayHistory);
	}

	
}
