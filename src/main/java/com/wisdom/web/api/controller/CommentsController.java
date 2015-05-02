package com.wisdom.web.api.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.common.model.Comments;
import com.wisdom.community.service.ICommentsService;
import com.wisdom.web.utils.ErrorCode;

@Controller
public class CommentsController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommentsController.class);

	@Autowired
	private ICommentsService commentsService;
	
	@RequestMapping("/community/getCommentsByCommunityId")
	//@RequestMapping("/getCommentsByCommunityId")
	@ResponseBody
	public List<Map<String, String>> getCommentsByCommunityId(HttpServletRequest request) {
		logger.debug("enter getCommentsByCommunityId");
		String communityId = request.getParameter("communityId");
		List<Map<String, String>> retList = new ArrayList<>();
		if(communityId != null && !communityId.isEmpty()) {
			retList = commentsService.getCommentsByCommunityId(Long.valueOf(communityId));
		}
		logger.debug("finish getCommentsByCommunityId");
		return retList;
	}
	
	@RequestMapping("/community/getCommentsByUserId")
	//@RequestMapping("/getCommentsByUserId")
	@ResponseBody
	public List<Map<String, String>> getCommentsByUserId(HttpServletRequest request) {
		logger.debug("enter getCommentsByUserId");
		String userId = request.getParameter("userId");
		List<Map<String, String>> retList = new ArrayList<>();
		if(userId != null && !userId.isEmpty()) {
			retList = commentsService.getCommnetsByUserId(userId);
		}
		logger.debug("finish getCommentsByUserId");
		return retList;
	}
	
	@RequestMapping("/community/publishComment")
	//@RequestMapping("/publishComment")
	@ResponseBody
	public Map<Integer, String> publishComment(HttpServletRequest request) {
		logger.debug("enter publishComment");
		String userId = (String)request.getSession().getAttribute("userId");
		String communityId = request.getParameter("communityId");
		String comment = request.getParameter("comment");
		Map<Integer, String> retMap = new HashMap<>();
		boolean publishSuccess = false;
		if(userId != null && !userId.isEmpty()
				&& communityId != null && !communityId.isEmpty()
				&& comment != null && !comment.isEmpty()) {
			Comments comm = new Comments();
			comm.setComment(comment);
			comm.setCommunityId(Long.valueOf(communityId));
			comm.setCreateTime(new Timestamp(System.currentTimeMillis()));
			comm.setUserId(userId);
			publishSuccess = commentsService.addComments(comm);
		}
		if (publishSuccess) {
			retMap.put(ErrorCode.NO_ERROR_CODE, ErrorCode.NO_ERROR_MESSAGE);
		} else {
			retMap.put(ErrorCode.COMMENT_PUBLISH_ERROR_CODE,
					ErrorCode.COMMENT_PUBLISH_ERROR_MESSAGE);
		}
		logger.debug("finish publishComment");
		return retMap;
	}
	
}