package com.wisdom.web.api.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public String uploadCompanyBill(
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/files/company");
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("realPath", realPath);
		logger.debug("params : {}", params.toString());
		if (multipartRequest != null) {
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile multifile = multipartRequest
						.getFile((String) iterator.next());
				companyBillApi.uploadCompanyBill(params, multifile);
			}
		}
		return new HashMap<String, String>().put("url", "");
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
	
	@RequestMapping("/company/deleteCompanyBill")
	@ResponseBody
	public Map<String, String> deleteCompanyBill(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String idList = (String)request.getParameter("deleteIdList");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/files/company");
		if(companyBillApi.deleteCompanyBill(idList, realPath)) {
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
		if(companyBillApi.modifyCompanyBill(id, amount, type)) {
			retMap.put("error_code", "0");
		} else {
			 retMap.put("error_code", "1");
		}
		return retMap;
	}
}