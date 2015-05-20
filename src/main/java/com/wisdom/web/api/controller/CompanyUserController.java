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
	
	@RequestMapping("/companyUser/getAllCompanyUserDraftBill")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyUserDraftBill(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String status = (String)request.getParameter("status");
		String page = (String)request.getParameter("page");
		String pageSize = (String)request.getParameter("pageSize");
		
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("status", status);
		params.put("page", page);
		params.put("pageSize", pageSize);
		logger.debug(params.toString());
		return companyUserService.getCompanyUserInvoiceByStatus(params);
	}
	
	@RequestMapping("/companyUser/getUserInfo")
	@ResponseBody
	public Map<String, String> getUserInfo(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		return companyUserService.getUserInfo(userId);
	}
	
	@RequestMapping("/companyUser/updateInvoiceInfo")
	@ResponseBody
	public Map<String, String> updateInvoiceInfo(HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		Map<String, String> retMap = new HashMap<>();
		params.put("amount", (String)request.getParameter("amount"));
		params.put("expenseTypeId", (String)request.getParameter("expenseTypeId"));
		params.put("detailDesc", (String)request.getParameter("detailDesc"));
		params.put("invoiceCode", (String)request.getParameter("invoiceCode"));
		params.put("invoiceId", (String)request.getParameter("invoiceId"));
		if(companyUserService.updateInvoiceInfo(params)) {
			retMap.put("error_code", "0");
		} else {
			retMap.put("error_code", "1");
			retMap.put("error_message", "更新发票信息失败！");
		}
		return retMap;
	}
	
	@RequestMapping("/companyUser/submitInvoiceAudit")
	@ResponseBody
	public Map<String, String> submitInvoiceAudit(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute("userId");
		String idList = (String)request.getParameter("invoiceIdList");
		if(idList == null || idList.isEmpty()) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "提交的需要审核的发票列表为空！");
			return retMap;
		}
		if(companyUserService.submitInvoiceAudit(userId, idList)) {
			retMap.put("error_code", "0");
		} else {
			retMap.put("error_code", "2");
			retMap.put("error_message", "更新发票信息失败！");
		}
		return retMap;
	}
}
