package com.wisdom.web.api.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.company.service.IDeptService;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.user.service.IUserDeptService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.ICompanyUserApi;

@Service("companyUserService")
public class CompanyUserApiImpl implements ICompanyUserApi {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyUserApiImpl.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private IDeptService deptService;

	@Autowired
	private IUserDeptService userDeptService;

	@Autowired
	private IInvoiceService invoiceService;

	@Override
	public Map<String, String> uploadCompanyUserBill(
			Map<String, String> params, MultipartFile file) {
		Map<String, String> retMap = new HashMap<>();
		try {
			String userId = params.get("userId");
			long deptId = userDeptService.getDeptIdByUserId(userId);
			String costCenterCode = deptService.getCostCenterCodeById(deptId);
			String fileName = getGernarateFileName(file, userId);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					params.get("realPath"), fileName));
			Map<String, Object> invoiceRetMap = invoiceService
					.createDraftInvoice(userId,
							"../../img/billImg/" + fileName, costCenterCode);
			if (invoiceRetMap != null && (boolean) invoiceRetMap.get("success")) {
				retMap.put("error_code", "0");
			} else {
				retMap.put("error_code", "1");
				retMap.put("error_message", "上传发票失败，请稍后重试！");
			}
		} catch (IOException e) {
			logger.debug("uploadCompanyUserBill exception : {}", e.toString());
			retMap.put("error_code", "2");
			retMap.put("error_message", "上传发票失败，请稍后重试！");
		}
		return retMap;
	}

	private String getGernarateFileName(MultipartFile file, String userId) {
		Random rdm = new Random(System.currentTimeMillis());
		String extendName = file.getOriginalFilename().substring(
				file.getOriginalFilename().indexOf(".") + 1);
		return userId + System.currentTimeMillis() + Math.abs(rdm.nextInt())
				% 1000 + (extendName == null ? ".unknown" : "." + extendName);
	}
}
