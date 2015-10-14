package com.wisdom.company.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CompanyFixedAssets;
import com.wisdom.company.dao.ICompanyFixedDao;
import com.wisdom.company.service.IcompanyFixedService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.impl.CompanyBillApiImpl;

@Service("CompanyFixedService")
public class CompanyFixedServiceImpl implements IcompanyFixedService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CompanyFixedServiceImpl.class);
	
	@Autowired
	public ICompanyFixedDao companyFixedDao;
	
	@Autowired
	public IUserService userService;
	
	@Override
	public List<Map<String, String>> getCompanyFixedAssets(String userId, String month) {
		List<Map<String, String>> retList=new ArrayList<>();
		long companyId = userService.getCompanyIdByUserId(userId);
		if(companyId <= 0) return retList;
		List<CompanyFixedAssets> cfaList = companyFixedDao.getCompanyFixedAssets(companyId, month);
		for(CompanyFixedAssets cfa : cfaList) {
			Map<String, String> map = new HashMap<>();
			map.put("month", cfa.getMonth());
			map.put("name", cfa.getName());
			map.put("amortizationDepreciationMonth", Double.toString(cfa.getAmortizationDepreciationMonth()));
			map.put("amortizationMonth", Integer.toString(cfa.getAmortizationMonth()));
			map.put("assetsValue", Double.toString(cfa.getAssetsValue()));
			map.put("category", Integer.toString(cfa.getCategory()));
			map.put("cumulativeClepreciation",Double.toString( cfa.getCumulativeClepreciation()));
			map.put("expectRemainingValue", Double.toString(cfa.getExpectRemainingValue()));
			map.put("monthDepreciationValue", Double.toString(cfa.getMonthDepreciationValue()));
			map.put("netWorth", Double.toString(cfa.getNetWorth()));
			map.put("provisionDepreciation", Double.toString(cfa.getProvisionDepreciation()));
			map.put("remainingRate",Double.toString( cfa.getRemainingRate()));
			retList.add(map);
		}
		return retList;
	}

}
