package com.wisdom.web.api.controller;

import java.util.HashMap;
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

import com.wisdom.web.api.ICompanyUserApi;

@Controller
public class CompanyUserController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CompanyUserController.class);
	
	@Autowired
	private ICompanyUserApi companyUserService;
	
	@RequestMapping("/companyUser/uploadCompanyUserBill")
	@ResponseBody
	public Map<String, String> uploadSalary(@RequestParam("files") MultipartFile file, HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/img/billImg");
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("realPath", realPath);
		logger.debug(params.toString());
		return companyUserService.uploadCompanyUserBill(params, file);
	}
}
