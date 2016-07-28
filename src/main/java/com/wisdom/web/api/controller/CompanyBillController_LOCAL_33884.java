package com.wisdom.web.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.wisdom.common.model.TestInvoice;
import com.wisdom.invoice.service.IInvoiceService;
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

	@Autowired
	private IInvoiceService invoiceService;
	/*
	 * @RequestMapping("/company/uploadCompanyBill") public String
	 * uploadCompanyBill(@RequestParam("files") MultipartFile file,
	 * HttpServletRequest request) { String userId = (String)
	 * request.getSession().getAttribute("userId"); String realPath =
	 * request.getSession
	 * ().getServletContext().getRealPath("/WEB-INF/files/company"); Map<String,
	 * String> params = new HashMap<>(); params.put("userId", userId);
	 * params.put("realPath", realPath); logger.debug("params : {}",
	 * params.toString()); Map<String, String> retMap =
	 * companyBillApi.uploadCompanySalary(params, file);
	 * if(("0").equals(retMap.get("error_code"))) { return
	 * "redirect:/views/webviews/company/operate_success.html"; } else { return
	 * "redirect:/views/webviews/company/operate_fail.html"; } }
	 */

	@RequestMapping("/company/uploadCompanyBill")
	@ResponseBody
	public Map<String, String> uploadCompanyBill(
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		logger.debug("entrance uploadCompanyBill");
		String userId = (String) request.getSession().getAttribute("userId");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF").substring(0);
		String date=request.getParameter("bill_date");
		String supplyName = request.getParameter("supplyName");
		String isFixedAssets = request.getParameter("isFixedAssets");
//		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		realPath = "/files/company";
		logger.debug("uploadCompanyBill realPath : {}", realPath);
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("realPath", realPath);
		params.put("date", date);
		params.put("supplyName", supplyName);
		params.put("isFixedAssets", isFixedAssets);
		logger.debug("params : {}", params.toString());
		if (multipartRequest != null) {
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile multifile = multipartRequest
						.getFile((String) iterator.next());
				companyBillApi.uploadCompanyBill(params, multifile);
			}
		}
		Map<String, String> retMap = new HashMap<>();
		retMap.put("url", "url");
		return retMap;
	}

	@RequestMapping("/company/getAllCompanyBill")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyBankSta(
			HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> params = new HashMap<>();
		params.put("conditionValue", request.getParameter("conditionValue"));
		params.put("conditionType", request.getParameter("conditionType"));
		params.put("userId", userId);
		logger.debug("params : {}", params.toString());
		return companyBillApi.getCompanyBillByCondition(params);
	}
	
	@RequestMapping("/company/getAllInvoices")
	@ResponseBody
	public List<Map<String, String>> getAllInvoices(HttpServletRequest request){
		String userId = (String)request.getSession().getAttribute("userId");
		return companyBillApi.getAllInvoicesByUserId(userId);
	}
	

	@RequestMapping("/company/getAllInvoicesByCondition")
	@ResponseBody
	public List<Map<String, String>> getAllInvoicesByCondition(
			HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> params = new HashMap<>();
		params.put("conditionValue", request.getParameter("conditionValue"));
		params.put("conditionType", request.getParameter("conditionType"));
		params.put("userId", userId);
		logger.debug("params : {}", params.toString());
		return companyBillApi.getAllInvoicesByCondition(params, userId);
	}
	
	@RequestMapping("/company/deleteCompanyBill")
	@ResponseBody
	public Map<String, String> deleteCompanyBill(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String idList = (String)request.getParameter("deleteIdList");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/files/company");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		if(companyBillApi.deleteInvoice(idList, realPath)) {
			retMap.put("error_code", "0");
		} else {
			retMap.put("error_code", "1");
		}
		return retMap;
	}
	
	@RequestMapping("/company/modifyCompanyBill")
	@ResponseBody
	public Map<String, String> modifyCompanyBill(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String id = (String)request.getParameter("id");
		String amount = (String)request.getParameter("amount");
		String type = (String)request.getParameter("type");
		String supplyName = (String)request.getParameter("supplyName");
		String isFixedAssets = (String)request.getParameter("isFixedAssets");
		Long invoiceId = Long.parseLong(id);
		List<Map<String, String>> contentList = new ArrayList<>();
		Map<String, String> content = new HashMap<>();
		content.put("description", type);
		content.put("amount", amount);
		content.put("supplier", supplyName);
		contentList.add(content);
		Boolean fA = Boolean.valueOf(isFixedAssets);
		String itemId = UUID.randomUUID().toString();
		try {
			invoiceService.addInvoiceArtifact(invoiceId, contentList, itemId);
			invoiceService.setIsFAOfInvoice(invoiceId, fA, itemId);
			retMap.put("error_code", "0");
		}catch(Exception e){
			retMap.put("error_code", "1");
		}
		return retMap;
	}
	
	@RequestMapping("/getUngeneratedInvoiceImages")
	@ResponseBody
	public Map<String, String> getUngeneratedInvoiceImages(HttpServletRequest request){
		Map<String, String> retMap = new HashMap<>();
		int limit = Integer.parseInt(request.getParameter("limit"));
		List<TestInvoice> invoices = invoiceService.getUngeneratedInvoiceImages(limit);
		for(TestInvoice invoice: invoices){
			retMap.put(invoice.getId().toString(), invoice.getFileName());
		}
		return retMap;
		
	}
	
	@RequestMapping("/setInvoiceImageToGenerated")
	@ResponseBody
	public Map<String, String> setInvoiceImageToGenerated(HttpServletRequest request){
		long invoiceId = Long.parseLong(request.getParameter("invoice_id"));
		Map<String, String> retMap = new HashMap<>();
		if(invoiceService.setInvoiceImageToGenerated(invoiceId)){
			retMap.put("error_code", "0");
		}else{
			retMap.put("error_code", "1");
		}
		return retMap;
	}
	
	@RequestMapping("/setInvoiceImageToInvalid")
	@ResponseBody
	public Map<String, String> setInvoiceImageToInvalid(HttpServletRequest request){
		long invoiceId = Long.parseLong(request.getParameter("invoice_id"));
		Map<String, String> retMap = new HashMap<>();
		if(invoiceService.setInvoiceImageToInvalid(invoiceId)){
			retMap.put("error_code", "0");
		}else{
			retMap.put("error_code", "1");
		}
		return retMap;
	}
}
