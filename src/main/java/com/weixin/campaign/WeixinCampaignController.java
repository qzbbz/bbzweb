package com.weixin.campaign;
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

import com.wisdom.weixin.utils.WeixinConstEnum;
import com.wisdom.weixin.utils.WeixinTools;

@Controller
public class WeixinCampaignController {

	private List<WeixinCampaignSubjectModel> wcsmList = null;

	private IWeixinCampaignDao weixinCampaignDao;

	private static final Logger logger = LoggerFactory.getLogger(WeixinCampaignController.class);

	@Autowired
	public WeixinCampaignController(IWeixinCampaignDao weixinCampaignDao) {
		this.weixinCampaignDao = weixinCampaignDao;
		wcsmList = weixinCampaignDao.getSubjects(3);
	}
	
	@Scheduled(cron="0 15 10 ? * *")
	public void updateSubjectList() {
		wcsmList = weixinCampaignDao.getSubjects(3);
		weixinCampaignDao.resetAllUserAnswerStatus();
	}

	@RequestMapping("/weixinCampaign/weixinLogin")
	public String weixinAuthorizedLogin(HttpSession httpSession, HttpServletRequest request) {
		String code = request.getParameter("code");
		String accessToken = "";
		String openId = "";
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
		httpSession.setAttribute("subjectList", wcsmList);
		return "views/weixinviews/weixin_campaign";
	}

	@RequestMapping("/weixinCampaign/submitAnswers")
	public String submitAnswers(HttpSession httpSession, HttpServletRequest request) {
		
		return "views/weixinviews/";
	}

}
