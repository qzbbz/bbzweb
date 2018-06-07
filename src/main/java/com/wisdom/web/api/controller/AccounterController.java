package com.wisdom.web.api.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.URI;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.wisdom.accounter.service.IAccounterService;
import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.Company;
import com.wisdom.common.model.CustomerTaoBao;
import com.wisdom.common.model.SalarySocialSecurity;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.ICompanyBillApi;
import com.wisdom.web.utils.Base64Converter;
import com.wisdom.web.utils.ErrorCode;
import com.wisdom.web.utils.SessionConstant;

@Controller
public class AccounterController {

	private static final Logger logger = LoggerFactory
			.getLogger(AccounterController.class);

	@Autowired
	private IAccounterService accounterService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICompanyBillApi companyBillApi;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IInvoiceService invoiceService;

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
		Map<Long, String> retMap = accounterService
				.getAllAccounterCertificate();
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
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		Map<String, String> retMap = new HashMap<>();
		if (userId != null && !userId.isEmpty()) {
			retMap = accounterService.getAccounterByUserId(userId);
		}
		logger.debug("finish getAccounterInfo");
		return retMap;
	}
	
	@RequestMapping("/getAllAccounter")
	@ResponseBody
	public List<Map<String, String>> getAllAccounter(HttpServletRequest request) {
		logger.debug("enter getAllAccounter");
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		List<Map<String, String>> retMap = new ArrayList<>();
		if (userId != null && !userId.isEmpty()) {
			retMap = accounterService.getAllAccounter();
		}
		logger.debug("finish getAllAccounter");
		return retMap;
	}
	
	@RequestMapping("/getAllAccounterByCondition")
	@ResponseBody
	public List<Map<String, String>> getAllAccounterByCondition(HttpServletRequest request) {
		logger.debug("enter getAllAccounterByCondition");
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String industry = request.getParameter("industry");
		String career = request.getParameter("career");
		List<Map<String, String>> retMap = new ArrayList<>();
		if (userId != null && !userId.isEmpty()) {
			retMap = accounterService.getAllAccounterByCondition(province, city, area, industry, career);
		}
		logger.debug("finish getAllAccounterByCondition");
		return retMap;
	}
	
	@RequestMapping("/accounter/uploadPhotoDraft")
	@ResponseBody
	public List<Map<String, String>> uploadPhotoDraft(HttpServletRequest request) {
		logger.debug("enter getAllAccounterByCondition");
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String industry = request.getParameter("industry");
		String career = request.getParameter("career");
		List<Map<String, String>> retMap = new ArrayList<>();
		if (userId != null && !userId.isEmpty()) {
			retMap = accounterService.getAllAccounterByCondition(province, city, area, industry, career);
		}
		logger.debug("finish getAllAccounterByCondition");
		return retMap;
	}

	@RequestMapping("/accounter/getCustomerManagementDetails")
	@ResponseBody
	public List<Map<String, String>> getCustomerManagementDetails(HttpServletRequest request) {
		logger.debug("enter getCustomerManagementDetails");
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		List<Map<String, String>> retMap = accounterService.getAllCustomer(userId);
		logger.debug("returen CustomerManagementDetails list size: {}", retMap.size());
		return retMap;
		
	}
	
	@RequestMapping("/accounter/getCustomerTaoBaoCountByMonth")
	@ResponseBody
	public Map<String, String> getCustomerTaoBaoCountByMonth(HttpServletRequest request) {
		logger.debug("enter getCustomerTaoBaoCountByMonth");
		String company_id = request.getParameter("company_id");
		String search_type = request.getParameter("search_type");
		int count = accounterService.getCustomerTaoBaoCountByMonth(Long.valueOf(company_id), Integer.valueOf(search_type));
		Map<String, String> retMap = new HashMap<>();
		retMap.put("count", String.valueOf(count));
		logger.debug("returen getCustomerTaoBaoCountByMonth count: {}", count);
		return retMap;
		
	}
	
	@RequestMapping("/accounter/uploadCustomerSnapshot")
	@ResponseBody
	public Map<String, String> uploadCustomerSnapshot(
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		logger.debug("enter uploadCustomerSnapshot");
		Map<String, String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute("userId");
		String company_id = request.getParameter("company_id");
		String taobao_accounter = request.getParameter("taobao_accounter");
		String realPath = "/home/files/taobao";
		if (multipartRequest != null) {
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile multifile = multipartRequest
						.getFile((String) iterator.next());
				String fileName = getGernarateFileName(multifile, userId);
				try {
					FileUtils.copyInputStreamToFile(multifile.getInputStream(),
							new File(realPath, fileName));
					CustomerTaoBao ctb = new CustomerTaoBao();
					ctb.setCompanyId(Long.valueOf(company_id));
					ctb.setCreateTime(new Timestamp(System.currentTimeMillis()));
					ctb.setFileName(fileName);
					ctb.setTaobaoAccounter(taobao_accounter);
					accounterService.addCustomerComment(ctb);
				} catch (IOException e) {
					logger.error("Failed in saving file, exception : {}", e.toString());
				}
			}
		}
		return retMap;
	}
	
	@RequestMapping("/accounter/updateAccounterInfo")
	public String updateAccounterInfo(
			@RequestParam("files") MultipartFile file, HttpServletRequest request) {
		logger.debug("enter getAccounterInfo");
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		String name = request.getParameter("userName");
		String phone = request.getParameter("userPhone");
		String province = request.getParameter("provinceName");
		String city = request.getParameter("cityName");
		String area = request.getParameter("areaName");
		String certificate = request.getParameter("certificateName");
		String industry = request.getParameter("industryName");
		String career = request.getParameter("careerName");
		String image = request.getParameter("image");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF").substring(0);
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/image";
		Map<Integer, String> retMap = new HashMap<>();
		boolean updateSuccess = false;
		if (userId != null && !userId.isEmpty()) {
			Accounter accounter = new Accounter();
			accounter.setUserId(userId);
			accounter.setProvince(province);
			accounter.setCity(city);
			accounter.setArea(area);
			accounter.setCertificate(certificate);
			accounter.setCareer(career);
			accounter.setIndustry(industry);
			accounter.setImage(image);
			if (!file.isEmpty()) {
				String fileName = getGernarateFileName(file, userId);
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(),
							new File(realPath, fileName));
					accounter.setImage(fileName);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			updateSuccess = accounterService.updateAccounter(accounter);
			updateSuccess = updateSuccess && userService.setUserNameByUserId(name, userId);
			updateSuccess = updateSuccess && userService.setUserPhone(userId, phone);
		}
		if (updateSuccess) {
			retMap.put(ErrorCode.NO_ERROR_CODE, ErrorCode.NO_ERROR_MESSAGE);
		} else {
			retMap.put(ErrorCode.ACCOUNTER_UPDATE_INFO_ERROR_CODE,
					ErrorCode.ACCOUNTER_UPDATE_INFO_ERROR_MESSAGE);
		}
		logger.debug("finish getAccounterInfo");
		return "redirect:/views/webviews/accounter/finish_reg_info.html";
	}

	@RequestMapping("/accounter/companyExpenseRecords")
	public String finishDeatilRegInfo(HttpServletRequest request) {
		return "redirect:/views/webviews/accounter/company_expense_records.html";
	}
	
	@RequestMapping("/accounter/getAllCompanyExpense")
	@ResponseBody
	public Map<String, List<Map<String, String>>> getAllCompanyExpense(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		return accounterService.getAllCompanyExpense(userId);
	}
	
	@RequestMapping("/accounter/getAllCompanyExpenseDataTable")
	@ResponseBody
	public Map<String, Object> getAllCompanyExpenseDataTable(HttpServletRequest request) {
		Map<String, Object> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		int start = 0;
		int length = 0;
		try {
			start = Integer.valueOf(request.getParameter("iDisplayStart"));
			length = Integer.valueOf(request.getParameter("iDisplayLength"));
		} catch(Exception e) {
			logger.debug(e.toString());
			return retMap;
		}
		List<Map<String, Object>> result = accounterService.getAllCompanyExpenseDataTable(userId, start, length);
		int recordTotal = accounterService.getAllCompanyExpenseRecordTotal(userId);
		retMap.put("data", result);
		retMap.put("iTotalRecords", recordTotal);
		retMap.put("iTotalDisplayRecords", recordTotal);
		return retMap;
	}
	
	@RequestMapping("/accounter/getAllCompanyExpenseDataTableByCondition")
	@ResponseBody
	public Map<String, Object> getAllCompanyExpenseDataTableByCondition(HttpServletRequest request) {
		Map<String, Object> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		int start = 0;
		int length = 0;
		try {
			start = Integer.valueOf(request.getParameter("start"));
			length = Integer.valueOf(request.getParameter("length"));
		} catch(Exception e) {
			logger.debug(e.toString());
			return retMap;
		}
		Map<String, String> conditions = new HashMap<>();
		conditions.put("companyName", request.getParameter("name"));
		conditions.put("created_time", request.getParameter("created_time"));
		conditions.put("item_type", request.getParameter("item_type"));
		List<Map<String, Object>> result = accounterService.getAllCompanyExpenseDataTableByCondition(userId, conditions, start, length);
		int count = accounterService.getAllCompanyExpenseByConditionRecordTotal(userId, conditions);
		retMap.put("data", result);
		retMap.put("iTotalRecords", count);
		retMap.put("iTotalDisplayRecords", count);
		return retMap;
	}
	
	@RequestMapping("/accounter/uploadCompanySalaryTemplate")
	public String uploadCompanySalaryTemplate(@RequestParam("files") MultipartFile file, HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		String companyId = request.getParameter("companyId");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF").substring(0);
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		accounterService.uploadCompanySalaryTemplate(companyId, realPath, file, userId);
		return "redirect:/views/webviews/accounter/company_salary_template_upload.html";
	}
	
	@RequestMapping("/accounter/getAllAccounterCompany")
	@ResponseBody
	public List<Map<String, String>> getAllAccounterCompany(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		return accounterService.getAllAccounterCompany(userId);
	}
	
	@RequestMapping("/accounter/getAllAccounterCompanyWithoutCondition")
	@ResponseBody
	public List<Map<String, String>> getAllAccounterCompanyWithoutCondition(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		return accounterService.getAllAccounterCompanyWithoutCondition(userId);
	}
	
	@RequestMapping("/accounter/getAllCompanyExpenseByCondition")
	@ResponseBody
	public Map<String, List<Map<String, String>>> getAllCompanyExpenseByCondition(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		Map conditions = request.getParameterMap();
		return accounterService.getAllCompanyExpenseByCondition(userId, conditions);
	}
	
	@RequestMapping("/accounter/getTakeBillWay")
	@ResponseBody
	public Map<String, String> getTakeBillWay(HttpServletRequest request) {
		String companyId = request.getParameter("companyId");
		return accounterService.getTakeBillWay(companyId);
	}
	
	@RequestMapping("/accounter/accounterHasBelongToCompany")
	@ResponseBody
	public Map<String, String> accounterHasBelongToCompany(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		return accounterService.accounterBelongToCompany(userId);
	}
	
	@RequestMapping("/accounter/accounterHasFinishRegister")
	@ResponseBody
	public Map<String, String> accounterHasFinishRegister(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		return accounterService.accounterHasFinishRegister(userId);
	}
	
	@RequestMapping("/accounter/downloadCompanyExpense")
	public ResponseEntity<byte[]> downloadCompanyExpense(HttpServletRequest request) {
		String fileName = request.getParameter("fileName");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF").substring(0);
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";	
		ResponseEntity<byte[]> re = null;
		File file = new File(realPath + "/" + fileName);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			re = new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(file), headers,
					HttpStatus.OK);
		} catch (IOException e) {
			logger.debug("download exception : {}", e.toString());
		}
		return re;
	}
	
	private String getGernarateFileName(MultipartFile file, String userId) {
		Random rdm = new Random(System.currentTimeMillis());
		String extendName = file.getOriginalFilename().substring(
				file.getOriginalFilename().indexOf(".") + 1);
		return userId + System.currentTimeMillis() + Math.abs(rdm.nextInt())
				% 1000 + (extendName == null ? ".unknown" : "." + extendName);
	}
	
	@RequestMapping("/accounter/getAllCompanyBill")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyBill(
			HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> params = new HashMap<>();
		params.put("conditionValue", request.getParameter("conditionValue"));
		params.put("conditionType", request.getParameter("conditionType"));
		params.put("userId", userId);
		logger.debug("params : {}", params.toString());
		return companyBillApi.accounterGetCompanyBillByCondition(params);
	}
	
	@RequestMapping("/accounter/modifyCompanyBill")
	@ResponseBody
	public Map<String, String> accounterModifyCompanyBill(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String id = (String)request.getParameter("id");
		String amount = (String)request.getParameter("amount");
		String type = (String)request.getParameter("type");
		String supplyName = (String)request.getParameter("supplyName");
		String tax = (String)request.getParameter("tax");
		String isFixedAssets = (String)request.getParameter("isFixedAssets");
		String number = (String)request.getParameter("number");
		Long invoiceId = Long.parseLong(id);
		List<Map<String, String>> contentList = new ArrayList<>();
		Map<String, String> content = new HashMap<>();
		content.put("description", type);
		content.put("amount", amount);
		content.put("supplier", supplyName);
		content.put("tax", tax);
		content.put("number", number);
		contentList.add(content);
		Boolean fA = false;
		if (isFixedAssets.equals("1")){
			fA = true;
		}
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
	
	@RequestMapping("/modifyCompanyBill")
	@ResponseBody
	public Map<String, String> demoAccounterModifyCompanyBill(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String id = (String)request.getParameter("id");
		String amount = (String)request.getParameter("amount");
		String type = (String)request.getParameter("type");
		String supplyName = (String)request.getParameter("supplyName");
		String tax = (String)request.getParameter("tax");
		String isFixedAssets = (String)request.getParameter("isFixedAssets");
		Long invoiceId = Long.parseLong(id);
		List<Map<String, String>> contentList = new ArrayList<>();
		Map<String, String> content = new HashMap<>();
		content.put("description", type);
		content.put("amount", amount);
		content.put("supplier", supplyName);
		content.put("tax", tax);
		content.put("number", "1");
		contentList.add(content);
		Boolean fA = false;
		if (isFixedAssets.equals("1")){
			fA = true;
		}
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
	
	@RequestMapping("/accounter/getAllCompany")
	@ResponseBody
	public Map<String, Object> getAllCompany(HttpServletRequest request) {
		Map<String, Object> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		List<Company> companyList = companyService.getCompanyListByAccounterId(userId);
		if(companyList == null || companyList.isEmpty()) {
			retMap.put("error_code", "-1");
			retMap.put("error_msg", "您还未负责任何公司财务！");
			return retMap;
		}
		List<Map<String, Object>> data = new ArrayList<>();
		for(Company c : companyList) {
			Map<String, Object> cMap = new HashMap<>();
			cMap.put("company_id", c.getId());
			cMap.put("company_name", c.getName());
			data.add(cMap);
		}
		retMap.put("data", data);
		return retMap;
	}
	
	@RequestMapping("/accounter/uploadCompanyBill")
	@ResponseBody
	public Map<String, String> uploadCompanyBill(DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String companyId = request.getParameter("company_id");
		String date = request.getParameter("bill_date");
		String isFA = request.getParameter("isFA");
		String realPath = "/home/files/company";
		String url = "http://120.132.27.211:8080/pcUploadInvoiceInfo?data=";
		if (multipartRequest != null) {
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile multifile = multipartRequest.getFile((String) iterator.next());
				String fileName = getGernarateFileName(multifile, userId);
				try {
					FileUtils.copyInputStreamToFile(multifile.getInputStream(), new File(
							realPath, fileName));
					url += (fileName + "," + companyId + "," + date + "," + isFA);
				} catch (IOException e) {
					logger.error("Failed in saving file, exception : {}", e.toString());
				}
			}
		}
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response;
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String res = EntityUtils.toString(entity, "utf-8");
			System.out.println("res : " + res);
			EntityUtils.consume(entity);  
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
 		Map<String, String> retMap = new HashMap<>();
		retMap.put("url", "url");
		return retMap;
	}
}
