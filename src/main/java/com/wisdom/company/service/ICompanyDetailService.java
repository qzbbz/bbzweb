package com.wisdom.company.service;

import java.util.List;

import com.wisdom.common.model.CompanyDetail;

public interface ICompanyDetailService {
	
	public CompanyDetail getCompanyDetailByCompanyId(long companyId);
	
	public long addCompanyDetail(CompanyDetail companyDetail);
	
	public boolean deleteCompanyDetailByCompanyId(long companyId);
	
	public boolean updateCompanyDetail(CompanyDetail companyDetail);
	
	public List<CompanyDetail> getAllCompanyDetail();
	
}
