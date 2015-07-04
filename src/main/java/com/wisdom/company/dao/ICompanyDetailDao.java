package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.CompanyDetail;

public interface ICompanyDetailDao {
	
	public CompanyDetail getCompanyDetailByCompanyId(long companyId);
	
	public List<CompanyDetail> getAllCompanyDetail();
	
	public long addCompanyDetail(CompanyDetail companyDetail);
	
	public boolean deleteCompanyDetailByCompanyId(long companyId);
	
	public boolean updateCompanyDetail(CompanyDetail companyDetail);
	
}
