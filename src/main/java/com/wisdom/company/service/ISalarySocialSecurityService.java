package com.wisdom.company.service;

import com.wisdom.common.model.SalarySocialSecurity;

public interface ISalarySocialSecurityService {

	public SalarySocialSecurity getSSSByCompanyIdAndCityNameAndRegType(long companyId, String cityName, int type);
	
	public boolean addSalarySocialSecurity(SalarySocialSecurity sss);
	
	public boolean deleteSalarySocialSecurity(SalarySocialSecurity sss);
	
	public boolean updateSalarySocialSecurity(SalarySocialSecurity sss);
	
	public boolean setTemplate(long companyId, String cityName, int type, String templatePath);
	
}
