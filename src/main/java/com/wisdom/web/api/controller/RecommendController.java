package com.wisdom.web.api.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.recommender.service.IRecommendService;
import com.wisdom.common.model.Recommender;
import com.wisdom.common.model.RecommendRecord;
import com.wisdom.web.utils.GenerateMD5;
import com.wisdom.web.utils.SessionConstant;

@Controller
public class RecommendController {

	private static final Logger logger = LoggerFactory
			.getLogger(RecommendController.class);
	
	@Autowired
	private IRecommendService recommendService;
	
	@RequestMapping("/recommend/getAllRecommenders")
	@ResponseBody
	public Map<String, String> getAllRecommender() {
		logger.debug("enter getAllRecommender");
		Map<String, String> retMap = recommendService.getAllRecommender();
		logger.debug("finish getAllRecommender");
		return retMap;
	}
	
	@RequestMapping("/recommend/addRecommender")
	@ResponseBody
	public Map<String, String> addRecommender(HttpServletRequest request) throws NoSuchAlgorithmException {
		logger.debug("enter addRecommender");
		Map<String, String> retMap = new HashMap<>();
		String recommenderName = request.getParameter("name");
		String md5 = GenerateMD5.generateMD5(recommenderName);
		Recommender recommender = new Recommender();
		recommender.setId(md5);
		recommender.setName(recommenderName);
		if(recommendService.addRecommender(recommender)){
			retMap.put("message", "ok");
		}
		else {
			retMap.put("message", "fail");
		}
		logger.debug("finish addRecommender");
		return retMap;
	}
	
	@RequestMapping("/recommend/getAllRecommendRecords")
	@ResponseBody
	public Map<String, List<String>> getRecommendRecord(HttpServletRequest request) {
		logger.debug("enter getRecommendRecord");
		Map<String, List<String>> retMap = recommendService.getAllRecommendRecord();
		logger.debug("finish getRecommendRecord");
		return retMap;
	}
	
	@RequestMapping("/recommend/addRecommendRecord")
	@ResponseBody
	public Map<String, String> addRecommendRecord(HttpServletRequest request) {
		logger.debug("enter addRecommendRecord");
		Map<String, String> retMap = new HashMap<>();
		String customerEmail = request.getParameter("email");
		Timestamp timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
		String recommenderId = request.getParameter("code");
		if (!recommendService.isRecommenderExisted(recommenderId)) {
			retMap.put("message", "fail");
		}
		else {
			RecommendRecord recommendRecord = new RecommendRecord();
			recommendRecord.setCreatedTime(timestamp);
			recommendRecord.setCustomerEmail(customerEmail);
			recommendRecord.setRecommenderId(recommenderId);
			recommendRecord.setIsPaid(0);
			if(recommendService.addRecommendRecord(recommendRecord)){
				retMap.put("message", "ok");
			}
			else{
			
				retMap.put("message", "fail");
			}
		}
		return retMap;
	}
	
	@RequestMapping("/recommend/isRecommended")
	@ResponseBody
	public Map<String, String> isRecommended(HttpSession httpSession, HttpServletRequest request) {
		String email = (String) httpSession.getAttribute(SessionConstant.SESSION_USER_ID);
		logger.info(httpSession.getAttributeNames().toString());
		logger.info("recommend controller email");
		logger.info(email);
		Map<String, String> retMap = new HashMap<>();
		if(recommendService.isRecommendRecordExisted(email) && !recommendService.isCustomerPaid(email)){
			retMap.put("message", "yes");
		}
		else{
			retMap.put("message", "no");
		}
		
		return retMap;
		
		
	}
	
	@RequestMapping("/recommend/addRecomendInfo")
	@ResponseBody
	public Map<String, String> addRecommendInfo(HttpServletRequest request) {
		String ip = request.getLocalAddr();
		String code = request.getParameter("code");
		Map<String, String> retMap = new HashMap<>();
		if(recommendService.addRecommendInfo(code, ip)){
			retMap.put("message", "ok");
		}
		else{
			retMap.put("message", "fail");
		}
		return retMap;
	}
	
}
