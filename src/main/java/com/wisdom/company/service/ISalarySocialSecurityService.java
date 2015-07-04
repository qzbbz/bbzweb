package com.wisdom.company.service;

import java.util.List;

import com.wisdom.common.model.SalarySocialSecurity;

public interface ISalarySocialSecurityService {

	public SalarySocialSecurity getSSSByCompanyIdAndCityNameAndRegType(long companyId, String cityName, int type);
	
	public SalarySocialSecurity getSSSById(long id);
	
	public boolean addSalarySocialSecurity(SalarySocialSecurity sss);
	
	public boolean deleteSalarySocialSecurity(SalarySocialSecurity sss);
	
	public boolean updateSalarySocialSecurity(SalarySocialSecurity sss);
	
	public boolean setTemplate(long companyId, String cityName, int type, String templatePath);
	
	public List<SalarySocialSecurity> getSSSByCompanyId(long companyId);
	
	public boolean setTemplate(long companyId, String fileName);
	
}
