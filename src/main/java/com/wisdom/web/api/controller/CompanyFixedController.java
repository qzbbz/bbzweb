package com.wisdom.web.api.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.web.api.ICompanyFixedApi;

@Controller
public class CompanyFixedController {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyFixedController.class);
	
	@Autowired
	private ICompanyFixedApi companyFixedapi;
	
	@RequestMapping("/company/getAllCompanyFixed")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyFixed(
			HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String month= request.getParameter("month");
		return	companyFixedapi.companyFixedInformation(userId,month);
	}
}
