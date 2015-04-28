package com.wisdom.web.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.accounter.service.IAccounterService;
import com.wisdom.web.utils.SessionConstant;

@Controller
public class AccounterController {

	private static final Logger logger = LoggerFactory
			.getLogger(AccounterController.class);
	
	@Autowired
	private IAccounterService accounterService;
	
	@RequestMapping("/getAllAccounterCareer")
	@ResponseBody
	public Map<Long, String> getAllAccounterCareer() {
		logger.debug("enter getAllAccounterCareer");
		Map<Long, String> retMap = accounterService.getAllAccounterCareer();
		logger.debug("finish getAllAccounterCareer");
		return retMap;
	}
	
	@RequestMapping("/getAllAccounterCertificate")
	@ResponseBody
	public Map<Long, String> getAllAccounterCertificate() {
		logger.debug("enter getAllAccounterCertificate");
		Map<Long, String> retMap = accounterService.getAllAccounterCertificate();
		logger.debug("finish getAllAccounterCertificate");
		return retMap;
	}
	
	@RequestMapping("/getAllAccounterIndustry")
	@ResponseBody
	public Map<Long, String> getAllAccounterIndustry() {
		logger.debug("enter getAllAccounterIndustry");
		Map<Long, String> retMap = accounterService.getAllAccounterIndustry();
		logger.debug("finish getAllAccounterIndustry");
		return retMap;
	}
	
	@RequestMapping("/getAccounterInfo")
	@ResponseBody
	public Map<String, String> getAccounterInfo(HttpServletRequest request) {
		logger.debug("enter getAccounterInfo");
		String userId = (String)request.getSession().getAttribute(SessionConstant.SESSION_USER_ID);
		Map<String, String> retMap = new HashMap<>();
		if(userId != null && !userId.isEmpty()) {
			retMap = accounterService.getAccounterByUserId(userId);
		}
		logger.debug("finish getAccounterInfo");
		return retMap;
	}
	
}
