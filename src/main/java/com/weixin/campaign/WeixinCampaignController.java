package com.weixin.campaign;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.weixin.utils.WeixinConstEnum;
import com.wisdom.weixin.utils.WeixinTools;

@Controller
public class WeixinCampaignController {

	@Autowired
	private IWeixinCampaignDao weixinCampaignDao;

	private static final Logger logger = LoggerFactory.getLogger(WeixinCampaignController.class);
	
	@Scheduled(cron="0 5 0 ? * *")
	public void updateSubjectList() {
		weixinCampaignDao.resetAllUserAnswerStatus();
	}

	@RequestMapping("/weixinCampaign/weixinLogin")
	public String weixinAuthorizedLogin(HttpSession httpSession, HttpServletRequest request) {
		String code = request.getParameter("code");
		String accessToken = "";
		String openId = (String)httpSession.getAttribute("openid");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		url = url.replace("APPID", WeixinConstEnum.COMPANY_APP_ID.toString())
				.replace("SECRET", WeixinConstEnum.COMPANY_APP_SECRET.toString()).replace("CODE", code);
		Map<Object, Object> map = WeixinTools.httpGet(url);
		if (map != null && map.containsKey("access_token") && map.containsKey("refresh_token")) {
			accessToken = (String) map.get("access_token");
			openId = (String) map.get("openid");
			url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
			url = url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
			Map<Object, Object> retMap = WeixinTools.httpGet(url);
			httpSession.setAttribute("openid", retMap.get("openid"));
			httpSession.setAttribute("nickname", retMap.get("nickname"));
			httpSession.setAttribute("headimgurl", retMap.get("headimgurl"));
			logger.debug("Auth openid : {}", openId);
			logger.debug("Auth openid 2: {}", retMap.get("openid"));
		}
		WeixinCampaignUserModel wcum = weixinCampaignDao.getUserModelByOpenId(openId);
		if(wcum == null || wcum.getHasAnswer() != 1) {
			httpSession.setAttribute("hasAnswer", "0");
		} else {
			httpSession.setAttribute("hasAnswer", "1");
		}
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		List<WeixinCampaignSubjectModel> retList = weixinCampaignDao.getSubjects(ts.toString().substring(0, 10));
		if(retList != null && retList.size() != 0) {
			httpSession.setAttribute("campaign_finish", "0");
		} else {
			httpSession.setAttribute("campaign_finish", "1");
		}
		return "views/weixinviews/weixin_campaign_main";
	}

	@RequestMapping("/weixinCampaign/submitAnswers")
	@ResponseBody
	public Map<String, String> submitAnswers(HttpSession httpSession, HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String openId = (String)httpSession.getAttribute("openid");
		String nickname = (String)httpSession.getAttribute("nickname");
		String rightCount = request.getParameter("rightCount");
		String spendTime = request.getParameter("spendTime");
		WeixinCampaignUserModel wcum = weixinCampaignDao.getUserModelByOpenId(openId);
		boolean isSuccess = false;
		if(wcum == null) {
			wcum = new WeixinCampaignUserModel();
			wcum.setAnswerRate(Long.valueOf(spendTime));
			wcum.setCreateTime(new Timestamp(System.currentTimeMillis()));
			wcum.setFinishCount(3);
			wcum.setHasAnswer(1);
			wcum.setRightCount(Integer.valueOf(rightCount));
			wcum.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			wcum.setUserId(openId);
			wcum.setUserName(nickname);
			isSuccess = weixinCampaignDao.insertRecord(wcum);
		} else {
			wcum.setAnswerRate(wcum.getAnswerRate() + Long.valueOf(spendTime));
			wcum.setFinishCount(wcum.getFinishCount() + 3);
			wcum.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			wcum.setRightCount(wcum.getRightCount() + Integer.valueOf(rightCount));
			wcum.setHasAnswer(1);
			isSuccess = weixinCampaignDao.updateRecord(wcum);
		}
		if(isSuccess) {
			retMap.put("status", "1");
		} else {
			retMap.put("status", "0");
		}
		return retMap;
	}
	
	@RequestMapping("/weixinCampaign/getSubjects")
	@ResponseBody
	public List<WeixinCampaignSubjectModel> getSubjects(HttpSession httpSession) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		List<WeixinCampaignSubjectModel> retList = weixinCampaignDao.getSubjects(ts.toString().substring(0, 10));
		if(retList == null) retList = new ArrayList<>();
		return retList;
	}
	
	@RequestMapping("/weixinCampaign/getAllUsers")
	@ResponseBody
	public List<WeixinCampaignUserModel> getAllUsers(HttpSession httpSession) {
		List<WeixinCampaignUserModel> retList = weixinCampaignDao.getAllUsers();
		if(retList == null) retList = new ArrayList<>();
		return retList;
	}

}
