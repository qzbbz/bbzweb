package com.wisdom.weixin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExpenseAccountController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonController.class);
	
	@RequestMapping("/checkBindCompany")
	@ResponseBody
	public Map<String, String> checkBindCompany(
			HttpServletRequest request) {
		logger.info("checkBindCompany");
		Map<String, String> result = new HashMap<>();
		String openId = request.getParameter("openId");
		if(openId == null || openId.isEmpty()) {
			result.put("error", "true");
			result.put("message", "checkBindCompany openId is null or empty!");
		} else {
			result.put("error", "false");
			/*CompanyUser user = companyUserService.findCompanyUserByOpenId(openId);
			if (user == null || user.getOpenId().isEmpty()) {
				result.put("message", "noBind");
			} else {
				result.put("message", "hasBind");
				result.put("companyName", user.getCompanyName());
				result.put("companyDepartment", user.getCompanyDepartment());
			}*/
		}
		logger.info("finishCheckBindCompany");
		logger.info("resultMap :{}", result.toString());
		return result;
	}
	
	@RequestMapping("/upload_bill")
	public ModelAndView uploadBill(HttpServletRequest request) {
		return new ModelAndView("upload_bill");
	}

	@RequestMapping("/my_bills")
	public ModelAndView myBills(HttpServletRequest request) {
		return new ModelAndView("my_bills");
	}

	@RequestMapping("/my_inbox")
	public ModelAndView myInbox(HttpServletRequest request) {
		return new ModelAndView("my_inbox");
	}
	
}
