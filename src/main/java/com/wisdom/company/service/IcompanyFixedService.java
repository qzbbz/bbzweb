package com.wisdom.company.service;

import java.util.List;
import java.util.Map;

import com.wisdom.common.model.CompanyFixedAssets;

public interface IcompanyFixedService {
	//get CompanyFixedAssets by companyId And month
		public List<Map<String, String>> getCompanyFixedAssets(String userId, String month);
}
