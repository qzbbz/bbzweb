package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.SalarySocialSecurity;

public interface ISalarySocialSecurityDao {
	
	//type 0:城镇，1：:农村
	public SalarySocialSecurity getSSSByCompanyIdAndCityNameAndRegType(long companyId, String cityName, int type);
	
	public List<SalarySocialSecurity> getSSSByCompanyId(long companyId);
	
	public boolean addSalarySocialSecurity(SalarySocialSecurity sss);
	
	public boolean deleteSalarySocialSecurity(SalarySocialSecurity sss);
	
	public boolean updateSalarySocialSecurity(SalarySocialSecurity sss);
	
	public boolean setTemplate(long companyId, String cityName, int type, String templatePath);
	
}
