package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.CompanyFixedAssets;

public interface ICompanyFixedDao {
	//get CompanyFixedAssets by companyId And month
	public 	List<CompanyFixedAssets> getCompanyFixedAssets(Long companyId,String month);
	
}
