package com.wisdom.weixin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.weixin.service.IExpenseAccountService;
import com.wisdom.weixin.utils.UploadBillEntity;
import com.wisdom.weixin.utils.UploadBillEntityWrapper;

@Controller
public class ExpenseAccountController {

	private static final Logger logger = LoggerFactory
			.getLogger(ExpenseAccountController.class);
	
	@Autowired
	private IExpenseAccountService expenseAccounterService;

	@RequestMapping(value="/downloadUserBill", method=RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public Map<String, String> downloadUserBill(@RequestBody UploadBillEntityWrapper wrapper, HttpServletRequest request) {
		String openId = request.getParameter("openId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/img/billImg");
		logger.debug("openId : {}", openId);
		Map<String, String> retMap = new HashMap<>();
		if(openId == null || openId.isEmpty()) {
			retMap.put("error_code", "3");
			retMap.put("error_message", "无法获取您微信的Openid，请稍后重新进入！");
		}
		List<UploadBillEntity> ube = wrapper.getUploadBillEntities();
		logger.debug("UploadBillEntity size : {}", ube.size());
		if(ube == null || ube.size() == 0) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "上传失败，服务器获取道德列表为空！");
			retMap.put("upload_count", "0");
		} else {
			int sum = 0;
			StringBuffer sb = new StringBuffer();
			for(UploadBillEntity up : ube) {
				logger.debug("mediaId : {}", up.getMediaId());
				logger.debug("amount : {}", up.getAmount());
				logger.debug("expenseTypeName : {}", up.getExpenseTypeName());
				logger.debug("expenseTypeId : {}", up.getExpenseTypeId());
				Map<String, Object> params = new HashMap<>();
				params.put("openId", openId);
				params.put("realPath", realPath);
				params.put("mediaId", up.getMediaId());
				params.put("expenseTypeId", Integer.valueOf(up.getExpenseTypeId()));
				params.put("amount", Double.valueOf(up.getAmount()));
				String base64ImageStr = expenseAccounterService.downloadFromUrl(params);
				if (!base64ImageStr.isEmpty()) {
					sum++;
					sb.append(up.getId());
					sb.append(" ");
				}
			}
			if(sum != ube.size()) {
				retMap.put("error_code", "2");
				retMap.put("error_message", "您选择上传的发票只有部分上传成功，请稍后重试！");
				retMap.put("upload_count", String.valueOf(sum) + "/" + String.valueOf(ube.size()));
			} else {
				retMap.put("error_code", "0");
				retMap.put("error_message", "全部上传成功！");
			}
			logger.debug("IDS : {}", sb.toString());
			if(sb.length() > 0) {
				retMap.put("ids", sb.toString().substring(0, sb.length() - 1));
			}
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
		logger.debug("status : {}", status);
		if (expenseAccounterService.approvalBill(approvalId, invoiceId, userId,
				Integer.valueOf(status))) {
			retMap.put("status", "success");
		} else {
			retMap.put("status", "fail");
		}
		logger.debug("retMap : {}", retMap.toString());
		return retMap;
	}

	@RequestMapping("/getNeedAuditBills")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getMyBills(
			HttpServletRequest request) {
		String openId = request.getParameter("openId");
		Map<String, List<Map<String, Object>>> retMap = expenseAccounterService
				.getNeedAuditBillsByOpenId(openId);
		logger.debug("getNeedAuditBills result : {}", retMap.toString());
		return retMap;
	}
	
	@RequestMapping("/getInboxBills")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getMyInbox(
			HttpServletRequest request) {
		String openId = request.getParameter("openId");
		Map<String, List<Map<String, Object>>> retMap = expenseAccounterService
				.getInboxBillsByOpenId(openId);
		logger.debug("getInboxBills result : {}", retMap.toString());
		return retMap;
	}
	
	@RequestMapping("/getAllExpenseType")
	@ResponseBody
	public List<Map<String, String>> getAllExpenseType(
			HttpServletRequest request) {
		List<Map<String, String>> retList = expenseAccounterService.getAllExpenseType();
		logger.debug("getAllExpenseType result : {}", retList.toString());
		return retList;
	}
	
	@RequestMapping(value="/submitBillListAudit", method=RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public Map<String, String> submitBillListAudit(
			@RequestBody List<String> invoiceIdList, HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String openId = request.getParameter("openId");
		logger.debug("openid : {}", openId);
		if(openId == null || openId.isEmpty()) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "无法获取您微信的Openid，请稍后重新进入！");
		}
		if(invoiceIdList == null || invoiceIdList.size() == 0) {
			retMap.put("error_code", "2");
			retMap.put("error_message", "提交失败，服务器接收到的申请列表为空，请检查！");
			logger.debug("invoiceIdList is null or size is 0");
		}
		int sum = 0;
		StringBuffer sb = new StringBuffer();
		for(String invoiceId : invoiceIdList) {
			if(expenseAccounterService.submitBillAudit(invoiceId)) {
				sum++;
				sb.append(invoiceId);
				sb.append(" ");
			}
		}
		if(sum != invoiceIdList.size()) {
			retMap.put("error_code", "3");
			retMap.put("error_message", "您提交的审核申请只有部分提交成功，请稍后重试！");
			retMap.put("submit_count", String.valueOf(sum) + "/" + String.valueOf(invoiceIdList.size()));
		} else {
			retMap.put("error_code", "0");
			retMap.put("error_message", "全部提交成功！");
		}
		logger.debug("IDS : {}", sb.toString());
		if(sb.length() > 0) {
			retMap.put("ids", sb.toString().substring(0, sb.length() - 1));
		}
		logger.debug("submitBillListAudit result : {}", retMap.toString());
		return retMap;
	}

}