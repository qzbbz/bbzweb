package com.wisdom.weixin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wisdom.common.model.ContactUs;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.contactus.utils.ContactUsDaoImpl;
import com.wisdom.contactus.utils.IContactUsDao;
import com.wisdom.user.service.IUserService;
import com.wisdom.user.service.IUserWeixinService;
import com.wisdom.weixin.service.IMessageProcessService;
import com.wisdom.weixin.service.ISettingService;
import com.wisdom.weixin.service.ITokenCheckService;
import com.wisdom.weixin.utils.WeixinJsonCode;
import com.wisdom.weixin.utils.WeixinTools;

@Controller
@SessionAttributes({ "userOpenId" })
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private ITokenCheckService tokenCheckService;

	@Autowired
	private IMessageProcessService messageProcessService;

	@Autowired
	private ISettingService settingService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IContactUsDao contactUsDao;

	@RequestMapping("/weixinRequest")
	@ResponseBody
	public String processWeixinRequest(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String echostr = request.getParameter("echostr");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String ret = "";
		logger.debug("receive a weixin request");
		if (echostr != null && !echostr.isEmpty()) {
			logger.debug("weixin request token check, signature:{}, echostr:{}, timestamp:{}, nonce:{}", signature,
					echostr, timestamp, nonce);
			ret = tokenCheckService.tokenCheck(signature, echostr, timestamp, nonce);
		} else {
			ret = messageProcessService.processWeixinMessage(request);
		}
		logger.debug("finish a weixin request");
		return ret;
	}

	@RequestMapping("/getJsConfigInfo")
	@ResponseBody
	public Map<String, String> getJsConfigInfo(HttpServletRequest request) {
		logger.debug("getJsConfigInfo");
		Map<String, String> result = new HashMap<>();
		String url = request.getParameter("url");
		logger.debug("url : {}", url);
		result = WeixinTools.getSign(url);
		logger.debug("finishGetJsConfigInfo");
		logger.debug("resultMap :{}", result.toString());
		return result;
	}

	@RequestMapping("/contactus")
	@ResponseBody
	public Map<String, String> contactus(HttpServletRequest request) {
		logger.debug("contactus");
		Map<String, String> result = new HashMap<>();
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String userCompanyName = request.getParameter("userCompanyName");
		ContactUs cus = new ContactUs();
		cus.setUserName(userName);
		cus.setUserPhone(userPhone);
		cus.setUserCompanyName(userCompanyName);
		contactUsDao.addContactUs(cus);
		logger.debug("finishcontactus");
		logger.debug("resultMap :{}", result.toString());
		return result;
	}

	@RequestMapping("/getOpenIdRedirect")
	public String getOpenIdRedirect(HttpSession session, HttpServletRequest request) {
		logger.debug("getOpenIdRedirect");
		String code = request.getParameter("code");
		String view = request.getParameter("view");
		String openId = "";
		if (code == null || code.isEmpty()) {
			session.setAttribute("userOpenId", "");
		} else {
			openId = WeixinTools.getOpenId(code);
			session.setAttribute("userOpenId", openId == null || openId.isEmpty() ? "" : openId);
		}
		logger.debug("finishGetOpenIdRedirect");
		logger.debug("code :{}, openId :{}, view :{}", code, openId, view);
		return "redirect:/views/weixinviews/" + view;
	}

	@RequestMapping("/getUserOpenId")
	@ResponseBody
	public Map<String, String> getUserOpenId(HttpSession session, HttpServletRequest request) {
		logger.debug("getUserOpenId");
		Map<String, String> result = new HashMap<>();
		String openId = "oSTV_t9z_fYa7AQVYO0y5-OMFavQ";//(String)session.getAttribute("openId");
		result.put("openId", openId);
		logger.debug("finishGetUserOpenId");
		logger.debug("resultMap :{}", result.toString());
		return result;
	}

	@RequestMapping("/getUserOpenIdAndCheckBindCompany")
	@ResponseBody
	public Map<String, Object> getUserOpenIdAndCheckBindCompany(HttpSession session, HttpServletRequest request) {
		logger.debug("getUserOpenIdAndCheckBindCompany");
		Map<String, Object> result = new HashMap<>();
		String type = request.getParameter("type");
		String openId = "oSTV_t9z_fYa7AQVYO0y5-OMFavQ";//(String)session.getAttribute("openId");
		result.put("openId", openId);
		if (openId == null || openId.isEmpty()) {
			result.put("error_code", String.valueOf(WeixinJsonCode.NO_OPENID_ERROR_CODE));
			result.put("error_message", WeixinJsonCode.NO_OPENID_ERROR_MESSAGE);
		} else {
			List<UserOpenid> openidList = userService.getUserIdByOpenId(openId);
			for (UserOpenid uoi : openidList) {
				int typeId = userService.getUserTypeIdByUserId(uoi.getUserId());
				if (result.containsKey("type_id")) {
					((Map<String, Object>) result.get("type_id")).put(uoi.getUserId(), typeId);
				} else {
					Map<String, Object> map = new HashMap<>();
					map.put(uoi.getUserId(), typeId);
					result.put("type_id", map);
				}
			}
			result.put("error_code", String.valueOf(WeixinJsonCode.NO_ERROR_CODE));
			result.put("error_message", WeixinJsonCode.NO_ERROR_MESSAGE);
			Map<String, Object> ret = settingService.checkCompanyBind(openId, type);
			result.putAll(ret);
		}
		logger.debug("getUserOpenIdAndCheckBindCompany");
		logger.debug("resultMap :{}", result.toString());
		return result;
	}

	@RequestMapping("/checkBindCompany")
	@ResponseBody
	public Map<String, Object> checkBindCompany(HttpServletRequest request) {
		logger.info("checkBindCompany");
		Map<String, Object> result = new HashMap<>();
		String openId = request.getParameter("openId");
		String type = request.getParameter("type");
		if (openId == null || openId.isEmpty()) {
			result.put("error_code", String.valueOf(WeixinJsonCode.NO_OPENID_ERROR_CODE));
			result.put("error_message", WeixinJsonCode.NO_OPENID_ERROR_MESSAGE);
		} else {
			result.put("error_code", String.valueOf(WeixinJsonCode.NO_ERROR_CODE));
			result.put("error_message", WeixinJsonCode.NO_ERROR_MESSAGE);
			Map<String, Object> ret = settingService.checkCompanyBind(openId, type);
			result.putAll(ret);
		}
		logger.info("finishCheckBindCompany");
		logger.info("resultMap :{}", result.toString());
		return result;
	}
}
