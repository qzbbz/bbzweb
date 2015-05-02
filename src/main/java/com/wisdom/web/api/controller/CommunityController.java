package com.wisdom.web.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.wisdom.common.model.Community;
import com.wisdom.community.service.ICommunityService;
import com.wisdom.web.utils.Base64Converter;
import com.wisdom.web.utils.ErrorCode;

@Controller
public class CommunityController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommunityController.class);

	@Autowired
	private ICommunityService communityService;

	@RequestMapping("/getAllCommunityTags")
	@ResponseBody
	public Map<Long, String> getAllCommunityTags() {
		logger.debug("enter getAllCommunityTags");
		Map<Long, String> retMap = communityService.getAllCommunityTags();
		logger.debug("finish getAllCommunityTags");
		return retMap;
	}

	@RequestMapping("/community/getCommunityByTagId")
	//@RequestMapping("/getCommunityByTagId")
	@ResponseBody
	public List<Map<String, String>> getCommunityByTagId(
			HttpServletRequest request) {
		logger.debug("enter getCommunityByTagId");
		String tagId = request.getParameter("tagId");
		List<Map<String, String>> retList = new ArrayList<>();
		if (tagId != null && !tagId.isEmpty()) {
			retList = communityService.getCommunityByTagId(Integer
					.valueOf(tagId));
		}
		logger.debug("finish getCommunityByTagId");
		return retList;
	}

	@RequestMapping("/community/getCommunityByKeywords")
	//@RequestMapping("/getCommunityByKeywords")
	@ResponseBody
	public List<Map<String, String>> getCommunityByKeywords(
			HttpServletRequest request) {
		logger.debug("enter getCommunityByKeywords");
		String keywords = request.getParameter("keywords");
		List<Map<String, String>> retList = new ArrayList<>();
		if (keywords != null && !keywords.isEmpty()) {
			retList = communityService.getCommunityByKeywords(keywords);
		}
		logger.debug("finish getCommunityByKeywords");
		return retList;
	}

	@RequestMapping("/community/getCommunityByOffset")
	//@RequestMapping("/getCommunityByOffset")
	@ResponseBody
	public List<Map<String, String>> getCommunityByOffset(
			HttpServletRequest request) {
		logger.debug("enter getCommunityByOffset");
		String startOffset = request.getParameter("startOffset");
		String endOffset = request.getParameter("endOffset");
		List<Map<String, String>> retList = new ArrayList<>();
		if (startOffset != null && !startOffset.isEmpty() && endOffset != null
				&& !endOffset.isEmpty()) {
			retList = communityService.getCommunityByOffset(
					Long.valueOf(startOffset), Long.valueOf(endOffset));
		}
		logger.debug("finish getCommunityByOffset");
		return retList;
	}

	@RequestMapping("/community/publishOrModifyArticle")
	//@RequestMapping("/publishOrModifyArticle")
	@ResponseBody
	public Map<Integer, String> publishOrModifyArticle(
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		logger.debug("enter publishOrModifyArticle");
		boolean publishSuccess = false;
		Map<Integer, String> retMap = new HashMap<>();
		String userId = (String)request.getSession().getAttribute("userId");
		String tagId = request.getParameter("tagId");
		String title = request.getParameter("title");
		String abstractData = request.getParameter("abstractData");
		String data = request.getParameter("data");
		String mode = request.getParameter("mode");
		if(userId != null && !userId.isEmpty() &&
				mode != null && !mode.isEmpty()) {
			Community community = new Community();
			community.setUserId(userId);
			community.setTagId(Long.valueOf(tagId));
			community.setTitle(title);
			community.setAbstractData(abstractData);
			community.setData(data);
			long time = System.currentTimeMillis();
			community.setUpdateTime(new Timestamp(time));
			if (!file.isEmpty()) {
				InputStream is = null;
				try {
					is = file.getInputStream();
					String imageStr = Base64Converter.imageToBase64(is);
					if (imageStr != null && !imageStr.isEmpty())
						community.setImage(imageStr);
				} catch (IOException e) {
					logger.debug(e.toString());
				}
			}
			if(mode.equals("PUB")) {
				community.setCreateTime(new Timestamp(time));
				publishSuccess = communityService.publishArticle(community);
			} else {
				publishSuccess = communityService.modifyArticle(community);
			}
		}
		if (publishSuccess) {
			retMap.put(ErrorCode.NO_ERROR_CODE, ErrorCode.NO_ERROR_MESSAGE);
		} else {
			retMap.put(ErrorCode.COMMUNITY_PUBLISH_ARTICLE_ERROR_CODE,
					ErrorCode.COMMUNITY_PUBLISH_ARTICLE_ERROR_MESSAGE);
		}
		logger.debug("finish publishOrModifyArticle");
		return retMap;
	}

}