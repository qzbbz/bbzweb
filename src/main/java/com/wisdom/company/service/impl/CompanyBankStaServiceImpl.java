package com.wisdom.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CompanyBankSta;
import com.wisdom.company.dao.ICompanyBankStaDao;
import com.wisdom.company.service.ICompanyBankStaService;

@Service("companyBankStaService")
public class CompanyBankStaServiceImpl implements ICompanyBankStaService {

	@Autowired
	private ICompanyBankStaDao companyBankStaDao;
	
	@Override
	public CompanyBankSta getCompanyBankStaByCompanyId(long companyId) {
		return companyBankStaDao.getCompanyBankStaByCompanyId(companyId);
	}

	@Override
	public long addCompanyBankSta(CompanyBankSta companyBankSta) {
		return companyBankStaDao.addCompanyBankSta(companyBankSta);
	}

	@Override
	public boolean deleteCompanyBankStaByCompanyId(long companyId) {
		return companyBankStaDao.deleteCompanyBankStaByCompanyId(companyId);
	}

	@Override
	public boolean updateCompanyBankSta(CompanyBankSta companyBankSta) {
		return companyBankStaDao.updateCompanyBankSta(companyBankSta);
	}

	@Override
	public boolean updateCompanyBankStaIdentifyStatusById(long id, int status) {
		return companyBankStaDao.updateCompanyBankStaIdentifyStatusById(id, status);
	}

}
