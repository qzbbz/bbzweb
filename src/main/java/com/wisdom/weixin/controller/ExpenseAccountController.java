package com.wisdom.weixin.controller;

import java.util.HashMap;
import java.util.List;
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
		String base64ImageStr = expenseAccounterService.downloadFromUrl(
				serverId, openId);
		if (!base64ImageStr.isEmpty()) {
			retMap.put("upload_status", "success");
		} else {
			retMap.put("upload_status", "fail");
		}
		logger.debug("retMap : {}", retMap.toString());
		return retMap;
	}

	@RequestMapping("/approvalBill")
	@ResponseBody
	public Map<String, String> approvalBill(HttpServletRequest request) {
		String approvalId = request.getParameter("approvalId");
		String invoiceId = request.getParameter("invoiceId");
		String userId = request.getParameter("userId");
		String status = request.getParameter("status");
		Map<String, String> retMap = new HashMap<>();
		logger.debug("approvalId : {}", approvalId);
		logger.debug("invoiceId : {}", invoiceId);
		logger.debug("userId : {}", userId);
		if (expenseAccounterService.approvalBill(approvalId, invoiceId, userId,
				Integer.valueOf(status))) {
			retMap.put("status", "success");
		} else {
			retMap.put("status", "fail");
		}
		logger.debug("retMap : {}", retMap.toString());
		return retMap;
	}

	@RequestMapping("/getMyBills")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getMyBills(
			HttpServletRequest request) {
		String openId = request.getParameter("openId");
		Map<String, List<Map<String, Object>>> retMap = expenseAccounterService
				.getBillsByOpenId(openId);
		logger.debug("getMyBills result : {}", retMap.toString());
		return retMap;
	}
	
	@RequestMapping("/getMyInbox")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getMyInbox(
			HttpServletRequest request) {
		String openId = request.getParameter("openId");
		Map<String, List<Map<String, Object>>> retMap = expenseAccounterService
				.getInboxByOpenId(openId);
		logger.debug("getMyInbox result : {}", retMap.toString());
		return retMap;
	}

}