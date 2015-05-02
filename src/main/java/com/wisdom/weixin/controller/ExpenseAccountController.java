package com.wisdom.weixin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.weixin.service.IExpenseAccountService;

@Controller
public class ExpenseAccountController {

	private static final Logger logger = LoggerFactory
			.getLogger(ExpenseAccountController.class);
	
	@Autowired
	private IExpenseAccountService expenseAccounterService;

	@RequestMapping("/downloadUserBill")
	@ResponseBody
	public Map<String, String> downloadUserBill(HttpServletRequest request) {
		String openId = request.getParameter("openId");
		String serverId = request.getParameter("serverId");
		Map<String, String> retMap = new HashMap<>();
		logger.debug("openId : {}", openId);
		logger.debug("serverId : {}", serverId);
		String base64ImageStr =  expenseAccounterService.downloadFromUrl(serverId);
		if(!base64ImageStr.isEmpty()) {
			retMap.put("upload_status", "success");
			
		} else {
			retMap.put("upload_status", "fail");
		}
		logger.debug("retMap : {}", retMap.toString());
		return retMap;
	}
	
	@RequestMapping("/getMyBills")
	@ResponseBody
	public Map<String, Map<String, String>> getMyBills(HttpServletRequest request) {
		String openId = request.getParameter("openId");
		return expenseAccounterService.getBillsByOpenId(openId);
	}

}
