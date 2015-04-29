package com.wisdom.web.api.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Company;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.user.service.IUserModifyService;
import com.wisdom.web.api.ICompanyDetailRegisterApi;
import com.wisdom.web.utils.ErrorCode;

@Service("companyDetailRegisterApiService")
public class CompanyDetailRegisterApiImpl implements
		ICompanyDetailRegisterApi {

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IUserModifyService userModifyService;

	@Override
	public Map<Integer, String> companyDetailRegister(String userId,
			Company company) {
		Map<Integer, String> retMap = new HashMap<>();
		long companyId = companyService.addCompany(company);
		if (userModifyService.modifyUserCompanyIdByUserId(userId, companyId)) {
			retMap.put(ErrorCode.NO_ERROR_CODE, ErrorCode.NO_ERROR_MESSAGE);
		} else {
			retMap.put(ErrorCode.COMPANY_REGISTER_ERROR_CODE,
					ErrorCode.COMPANY_REGISTER_ERROR_MESSAGE);
		}
		return retMap;
	}

}