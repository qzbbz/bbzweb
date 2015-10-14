package com.wisdom.web.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.company.service.IcompanyFixedService;
import com.wisdom.web.api.ICompanyFixedApi;
@Service("CompanyFixedApi")
public class CompanyFixedApiImpl implements ICompanyFixedApi {

	@Autowired
	private IcompanyFixedService companyFixedService;
	@Override
	public List<Map<String, String>> companyFixedInformation(String userId, String month) {
		/*Map<String, String> retMap = new HashMap<>();
		List list=new ArrayList();*/
//		String companyId=params.get("companyId");
//		if(companyId==null || companyId.isEmpty()){
//			return null;
//		}
//		String month=params.get("month");
//		if(month==null || month.isEmpty()){
//			return null;
//		}
		//list.add();
		return companyFixedService.getCompanyFixedAssets(userId, month);
	}

	
}
