package com.wisdom.web.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.ICompanyBillApi;

@Controller
public class CompanyBillController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CompanyBillController.class);

	@Autowired
	private ICompanyBillApi companyBillApi;

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/company/uploadCompanyBill")
	@ResponseBody
	public Map<String, String> uploadCompanyBill(@RequestParam("files") MultipartFile file, HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("realPath", realPath);
		logger.debug("params : {}", params.toString());
		return companyBillApi.uploadCompanySalary(params, file);
	}
	
	@RequestMapping("/company/getAllCompanyBill")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyBankSta(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> params = new HashMap<>();
		params.put("conditionValue", request.getParameter("conditionValue"));
		params.put("conditionType", request.getParameter("conditionType"));
		params.put("userId", userId);
		logger.debug("params : {}", params.toString());
		return companyBillApi.getCompanyBillByCondition(params);
	}
}
