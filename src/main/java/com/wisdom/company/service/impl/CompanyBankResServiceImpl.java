package com.wisdom.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CompanyBankRes;
import com.wisdom.company.dao.ICompanyBankResDao;
import com.wisdom.company.service.ICompanyBankResService;

@Service("CompanyBankResService")
public class CompanyBankResServiceImpl implements ICompanyBankResService {

	@Autowired
	private ICompanyBankResDao CompanyBankResDao;
	
	@Override
	public CompanyBankRes getCompanyBankResById(long id) {
		return CompanyBankResDao.getCompanyBankResById(id);
	}

	@Override
	public long addCompanyBankRes(CompanyBankRes CompanyBankRes) {
		return CompanyBankResDao.addCompanyBankRes(CompanyBankRes);
	}

	@Override
	public boolean deleteCompanyBankResByCompanyId(long id) {
		return CompanyBankResDao.deleteCompanyBankResById(id);
	}

	@Override
	public boolean updateCompanyBankRes(CompanyBankRes CompanyBankRes) {
		return CompanyBankResDao.updateCompanyBankRes(CompanyBankRes);
	}

	@Override
	public boolean updateCompanyBankResIdentifyStatusById(long id, int status) {
		return CompanyBankResDao.updateCompanyBankResIdentifyStatusById(id, status);
	}

	@Override
	public List<CompanyBankRes> getAllCompanyBankRes(long companyId) {
		return CompanyBankResDao.getAllCompanyBankRes(companyId);
	}

	@Override
	public List<CompanyBankRes> getAllCompanyBankResByDate(long companyId, String date) {
		return CompanyBankResDao.getAllCompanyBankResByDate(companyId, date);
	}

	@Override
	public List<CompanyBankRes> getAllCompanyBankResByIdentifyStatus(
			long companyId, int status) {
		return CompanyBankResDao.getAllCompanyBankResByIdentifyStatus(companyId, status);
	}

}
