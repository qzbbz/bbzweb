package com.wisdom.web.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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

import com.wisdom.accounter.service.IAccounterService;
import com.wisdom.common.model.Accounter;
import com.wisdom.web.utils.Base64Converter;
import com.wisdom.web.utils.ErrorCode;
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

	@RequestMapping("/updateAccounterInfo")
	@ResponseBody
	public Map<Integer, String> updateAccounterInfo(
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		logger.debug("enter getAccounterInfo");
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		String name = request.getParameter("name");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String certificate = request.getParameter("certificate");
		String industry = request.getParameter("industry");
		String career = request.getParameter("career");
		Map<Integer, String> retMap = new HashMap<>();
		boolean updateSuccess = false;
		if (userId != null && !userId.isEmpty()) {
			Accounter accounter = new Accounter();
			accounter.setUserId(userId);
			accounter.setName(name);
			accounter.setProvince(province);
			accounter.setCity(city);
			accounter.setArea(area);
			accounter.setCertificate(certificate);
			accounter.setCareer(career);
			accounter.setIndustry(industry);
			if (!file.isEmpty()) {
				InputStream is = null;
				try {
					is = file.getInputStream();
					String imageStr = Base64Converter.imageToBase64(is);
					if (imageStr != null && !imageStr.isEmpty())
						accounter.setImage(imageStr);
				} catch (IOException e) {
					logger.debug(e.toString());
				}
			}
			updateSuccess = accounterService.updateAccounter(accounter);
		}
		if (updateSuccess) {
			retMap.put(ErrorCode.NO_ERROR_CODE, ErrorCode.NO_ERROR_MESSAGE);
		} else {
			retMap.put(ErrorCode.ACCOUNTER_UPDATE_INFO_ERROR_CODE,
					ErrorCode.ACCOUNTER_UPDATE_INFO_ERROR_MESSAGE);
		}
		logger.debug("finish getAccounterInfo");
		return retMap;
	}

}
