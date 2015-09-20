package com.wisdom.web.api.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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

import com.wisdom.accounter.service.IAccounterService;
import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.SalarySocialSecurity;
import com.wisdom.user.service.IUserService;
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
	private IUserService userService;

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

	@RequestMapping("/accounter/finishDeatilRegInfo")
	public String finishDeatilRegInfo(HttpServletRequest request) {
		return "redirect:/views/webviews/accounter/finish_reg_info.html";
	}
	
	@RequestMapping("/accounter/getAllCompanyExpense")
	@ResponseBody
	public Map<String, List<Map<String, String>>> getAllCompanyExpense(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		return accounterService.getAllCompanyExpense(userId);
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
		String conditionType = request.getParameter("conditionType");
		String conditionValue = request.getParameter("conditionValue");
		return accounterService.getAllCompanyExpenseByCondition(userId, conditionType, conditionValue);
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
}
