package com.wisdom.web.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Company;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.web.api.IGenerateCostCenterEncodeApi;

@Service("generateCostCenterApiService")
public class GenerateCostCenterEncodeApiImpl implements IGenerateCostCenterEncodeApi {

	@Autowired
	private static ICompanyService companyService;
	
	private static Map<Long, Map<Long, Set<Long>>> companyToCostCenterEncodeMap = new HashMap<>();
	
	static {
		//List<Company> companyList = companyService.getAllCompany();
		
	}
	
	@Override
	public String generateCostCenterEncode(String parentCostCenterEncode) {
		// TODO Auto-generated method stub
		return null;
	}

}
