package com.wisdom.web.api.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.company.service.ICompanyBillService;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.ICompanyBillApi;

@Service("companyBillApiService")
public class CompanyBillApiImpl implements ICompanyBillApi {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyBillApiImpl.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICompanyBillService companyBillService;
	
	@Override
	public Map<String, String> uploadCompanyBill(Map<String, String> params, MultipartFile file) {
		Map<String, String> retMap = new HashMap<>();
		try {
			String userId = params.get("userId");
			long companyId = userService.getCompanyIdByUserId(userId);
			String fileName = getGernarateFileName(file, userId);
			FileUtils.copyInputStreamToFile(file.getInputStream(),
					new File(params.get("realPath"), fileName));
			CompanyBill cb = new CompanyBill();
			cb.setCompanyId(companyId);
			cb.setFileName(fileName);
			cb.setCreateTime(new Timestamp(System.currentTimeMillis()));
			companyBillService.addCompanyBill(cb);
			retMap.put("error_code", "0");
		} catch (IOException e) {
			logger.debug("uploadCompanyBill exception : {}", e.toString());
			retMap.put("error_code", "1");
			retMap.put("error_message", "上传发票失败，请稍后重试！");
		}
		return retMap;
	}

	@Override
	public List<Map<String, String>> getCompanyBillByCondition(
			Map<String, String> params) {
		String userId = params.get("userId");
		if(userId == null || userId.isEmpty()) return null;
		long companyId = userService.getCompanyIdByUserId(userId);
		if(companyId <= 0) return null;
		String companyName = companyService.getCompanyName(companyId);
		Map<String, String> extraParams = new HashMap<>();
		extraParams.put("company_name", companyName);
		if(params.get("conditionType") == null || params.get("conditionType").isEmpty() ||
				("0").equals(params.get("conditionType")) ||
				params.get("conditionValue") == null || params.get("conditionValue").isEmpty()) {
			List<CompanyBill> list = companyBillService.getAllCompanyBill(companyId);
			return createCompanyBillList(list, extraParams); 
		}
		if(("1").equals(params.get("conditionType")) && params.get("conditionValue") != null) {
			List<CompanyBill> list = companyBillService.getAllCompanyBillByDate(companyId, params.get("conditionValue"));
			return createCompanyBillList(list, extraParams);
		}
		return null;
	}

	private List<Map<String, String>> createCompanyBillList(List<CompanyBill> list, Map<String, String> extraParams) {
		List<Map<String, String>> retList = new ArrayList<>();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(list == null) return null;
		for(CompanyBill cb : list) {
			Map<String, String> map = new HashMap<>();
			Timestamp stamp = cb.getCreateTime();
			map.put("id", String.valueOf(cb.getId()));
			map.put("date", sdf.format(stamp));
			map.put("file_name", cb.getFileName() == null ? "" : cb.getFileName());
			map.putAll(extraParams);
			retList.add(map);
		}
		return retList.size() > 0 ? retList : null;
	}
	
	private String getGernarateFileName(MultipartFile file, String userId) {
		Random rdm = new Random(System.currentTimeMillis());
		String extendName = file.getOriginalFilename().substring(
				file.getOriginalFilename().indexOf(".") + 1);
		return userId + System.currentTimeMillis() + Math.abs(rdm.nextInt())
				% 1000 + (extendName == null ? ".unknown" : "." + extendName);
	}

	@Override
	public boolean deleteCompanyBill(String idList, String realPath) {
		String[] ids = idList.split(",");
		for (String id : ids) {
			CompanyBill cb = companyBillService.getCompanyBillById(Long.valueOf(id));
			if(cb == null) continue;
			File file = new File(realPath + "/" + cb.getFileName());
			FileUtils.deleteQuietly(file);
			companyBillService.deleteCompanyBillById(Long.valueOf(id));
		}
		return true;
	}

	@Override
	public boolean modifyCompanyBill(String id, String amount, String type) {
		
		return false;
	}
	
}
