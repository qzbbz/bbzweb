package com.wisdom.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.SalarySocialSecurity;
import com.wisdom.company.dao.ISalarySocialSecurityDao;
import com.wisdom.company.service.ISalarySocialSecurityService;

@Service("salarySocialSecurityService")
public class SalarySocialSecurityServiceImpl implements ISalarySocialSecurityService {

	@Autowired
	private ISalarySocialSecurityDao salarySocialSecurityDao;
	
	@Override
	public SalarySocialSecurity getSSSByCompanyIdAndCityNameAndRegType(
			long companyId, String cityName, int type) {
		return salarySocialSecurityDao.getSSSByCompanyIdAndCityNameAndRegType(companyId, cityName, type);
	}

	@Override
	public boolean addSalarySocialSecurity(SalarySocialSecurity sss) {
		return salarySocialSecurityDao.addSalarySocialSecurity(sss);
	}

	@Override
	public boolean deleteSalarySocialSecurity(SalarySocialSecurity sss) {
		return salarySocialSecurityDao.deleteSalarySocialSecurity(sss);
	}

	@Override
	public boolean updateSalarySocialSecurity(SalarySocialSecurity sss) {
		return salarySocialSecurityDao.updateSalarySocialSecurity(sss);
	}

	@Override
	public boolean setTemplate(long companyId, String cityName, int type,
			String templatePath) {
		return salarySocialSecurityDao.setTemplate(companyId, cityName, type, templatePath);
	}

	@Override
	public List<SalarySocialSecurity> getSSSByCompanyId(long companyId) {
		return salarySocialSecurityDao.getSSSByCompanyId(companyId);
	}

}
