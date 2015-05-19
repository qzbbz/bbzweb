package com.wisdom.company.service.impl;

import java.util.List;

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
	public CompanyBankSta getCompanyBankStaById(long id) {
		return companyBankStaDao.getCompanyBankStaById(id);
	}

	@Override
	public long addCompanyBankSta(CompanyBankSta companyBankSta) {
		return companyBankStaDao.addCompanyBankSta(companyBankSta);
	}

	@Override
	public boolean deleteCompanyBankStaByCompanyId(long id) {
		return companyBankStaDao.deleteCompanyBankStaById(id);
	}

	@Override
	public boolean updateCompanyBankSta(CompanyBankSta companyBankSta) {
		return companyBankStaDao.updateCompanyBankSta(companyBankSta);
	}

	@Override
	public boolean updateCompanyBankStaIdentifyStatusById(long id, int status) {
		return companyBankStaDao.updateCompanyBankStaIdentifyStatusById(id, status);
	}

	@Override
	public List<CompanyBankSta> getAllCompanyBankSta(long companyId) {
		return companyBankStaDao.getAllCompanyBankSta(companyId);
	}

	@Override
	public List<CompanyBankSta> getAllCompanyBankStaByDate(long companyId, String date) {
		return companyBankStaDao.getAllCompanyBankStaByDate(companyId, date);
	}

	@Override
	public List<CompanyBankSta> getAllCompanyBankStaByIdentifyStatus(
			long companyId, int status) {
		return companyBankStaDao.getAllCompanyBankStaByIdentifyStatus(companyId, status);
	}

}
