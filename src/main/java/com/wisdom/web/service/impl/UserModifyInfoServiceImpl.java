package com.wisdom.web.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.Company;
import com.wisdom.web.service.IUserModifyInfoService;

@Service("userModifyInfoService")
public class UserModifyInfoServiceImpl implements IUserModifyInfoService {

	@Override
	public Map<String, String> accounterModifyInfo(String userId,
			Accounter accounter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> companyModifyInfo(String userId, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

}