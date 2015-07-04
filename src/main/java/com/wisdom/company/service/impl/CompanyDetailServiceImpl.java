package com.wisdom.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CompanyDetail;
import com.wisdom.company.dao.ICompanyDetailDao;
import com.wisdom.company.service.ICompanyDetailService;

@Service("companyDetailService")
public class CompanyDetailServiceImpl implements ICompanyDetailService {

	@Autowired
	private ICompanyDetailDao companyDetailDao;
	
	@Override
	public CompanyDetail getCompanyDetailByCompanyId(long companyId) {
		return companyDetailDao.getCompanyDetailByCompanyId(companyId);
	}

	@Override
	public long addCompanyDetail(CompanyDetail companyDetail) {
		return companyDetailDao.addCompanyDetail(companyDetail);
	}

	@Override
	public boolean deleteCompanyDetailByCompanyId(long companyId) {
		return companyDetailDao.deleteCompanyDetailByCompanyId(companyId);
	}

	@Override
	public boolean updateCompanyDetail(CompanyDetail companyDetail) {
		return companyDetailDao.updateCompanyDetail(companyDetail);
	}

	@Override
	public List<CompanyDetail> getAllCompanyDetail() {
		return companyDetailDao.getAllCompanyDetail();
	}

}
