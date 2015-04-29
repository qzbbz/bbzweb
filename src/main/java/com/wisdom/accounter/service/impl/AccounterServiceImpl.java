package com.wisdom.accounter.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.accounter.dao.IAccounterCareerDao;
import com.wisdom.accounter.dao.IAccounterCertificateDao;
import com.wisdom.accounter.dao.IAccounterDao;
import com.wisdom.accounter.dao.IAccounterIndustryDao;
import com.wisdom.accounter.service.IAccounterService;
import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.AccounterCareer;
import com.wisdom.common.model.AccounterCertificate;
import com.wisdom.common.model.AccounterIndustry;

@Service("accounterService")
public class AccounterServiceImpl implements IAccounterService {

	@Autowired
	private IAccounterCareerDao accounterCareerDao;

	@Autowired
	private IAccounterCertificateDao accounterCertificateDao;

	@Autowired
	private IAccounterIndustryDao accounterIndustryDao;

	@Autowired
	private IAccounterDao accounterDao;

	@Override
	public Map<Long, String> getAllAccounterCareer() {
		Map<Long, String> careerMap = new HashMap<>();
		List<AccounterCareer> careerList = accounterCareerDao
				.getAllAccounterCareer();
		if (careerList != null && careerList.size() > 0) {
			for (AccounterCareer career : careerList) {
				careerMap.put(career.getId(), career.getName());
			}
		}
		return careerMap;
	}

	@Override
	public Map<Long, String> getAllAccounterCertificate() {
		Map<Long, String> certificateMap = new HashMap<>();
		List<AccounterCertificate> certificateList = accounterCertificateDao
				.getAllAccounterCertificate();
		if (certificateList != null && certificateList.size() > 0) {
			for (AccounterCertificate certificate : certificateList) {
				certificateMap.put(certificate.getId(), certificate.getName());
			}
		}
		return certificateMap;
	}

	@Override
	public Map<Long, String> getAllAccounterIndustry() {
		Map<Long, String> industryMap = new HashMap<>();
		List<AccounterIndustry> industryList = accounterIndustryDao
				.getAllAccounterIndustry();
		if (industryList != null && industryList.size() > 0) {
			for (AccounterIndustry industry : industryList) {
				industryMap.put(industry.getId(), industry.getName());
			}
		}
		return industryMap;
	}

	@Override
	public Map<String, String> getAccounterByUserId(String userId) {
		Map<String, String> accounterInfoMap = new HashMap<>();
		Accounter accounter = accounterDao.getAccounterByUserId(userId);
		if (accounter != null) {
			accounterInfoMap = accounterConvertToMap(accounter);
		}
		return accounterInfoMap;
	}

	@Override
	public boolean updateAccounter(Accounter accounter) {
		boolean updateSuccess = false;
		if(accounterDao.isAccounterExistByUserId(accounter.getUserId())) {
			updateSuccess = accounterDao.updateAccounter(accounter);
		} else {
			accounter.setCreateTime(new Timestamp(System.currentTimeMillis()));
			updateSuccess = accounterDao.addAccounter(accounter);
		}
		return updateSuccess;
	}

	@Override
	public List<Map<String, String>> getAllAccounter() {
		List<Map<String, String>> retList = new ArrayList<>();
		List<Accounter> list = accounterDao.getAllAccounter();
		if(list != null && list.size() > 0) {
			for(Accounter accounter : list)
				retList.add(accounterConvertToMap(accounter));
		}
		return retList;
	}
	
	private Map<String, String> accounterConvertToMap(Accounter accounter) {
		Map<String, String> map = new HashMap<>();
		map.put("user_id", accounter.getUserId());
		map.put("name", accounter.getName());
		map.put("area", accounter.getArea());
		map.put("city", accounter.getCity());
		map.put("province", accounter.getProvince());
		map.put("image", accounter.getImage());
		map.put("certificate_id", accounter.getCertificate());
		map.put("industry_id", accounter.getIndustry());
		map.put("career_id", accounter.getCareer());
		return map;
	}

}
