package com.wisdom.weixin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wisdom.weixin.service.IMessageProcessService;
import com.wisdom.weixin.service.ISettingService;
import com.wisdom.weixin.service.ITokenCheckService;
import com.wisdom.weixin.utils.WeixinJsonCode;
import com.wisdom.weixin.utils.WeixinTools;

@Controller
@SessionAttributes({ "userOpenId" })
public class CommonController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonController.class);

	@Autowired
	private ITokenCheckService tokenCheckService;

	@Autowired
	private IMessageProcessService messageProcessService;
	
	@Autowired
	private ISettingService settingService;

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
			logger.debug(
					"weixin request token check, signature:{}, echostr:{}, timestamp:{}, nonce:{}",
					signature, echostr, timestamp, nonce);
			ret = tokenCheckService.tokenCheck(signature, echostr, timestamp,
					nonce);
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

	@RequestMapping("/getOpenIdRedirect")
	public String getOpenIdRedirect(Model model,
			HttpServletRequest request) {
		logger.debug("getOpenIdRedirect");
		String code = request.getParameter("code");
		String view = request.getParameter("view");
		String openId = "";
		if (code == null || code.isEmpty()) {
			model.addAttribute("userOpenId", "");
		} else {
			openId = WeixinTools.getOpenId(code);
			model.addAttribute("userOpenId",
					openId == null || openId.isEmpty() ? "" : openId);
		}
		logger.debug("finishGetOpenIdRedirect");
		logger.debug("code :{}, openId :{}, view :{}", code, openId, view);
		return "redirect:/views/weixinviews/" + view;
	}

	@RequestMapping("/getUserOpenId")
	@ResponseBody
	public Map<String, String> getUserOpenId(Model model,
			HttpServletRequest request) {
		logger.debug("getUserOpenId");
		Map<String, String> result = new HashMap<>();
		result.put("openId", "");
		if (model.asMap().containsKey("userOpenId")) {
			result.put("openId", (String) model.asMap().get("userOpenId"));
		}
		logger.debug("finishGetUserOpenId");
		logger.debug("resultMap :{}", result.toString());
		return result;
	}
	
	@RequestMapping("/checkBindCompany")
	@ResponseBody
	public Map<String, String> checkBindCompany(HttpServletRequest request) {
		logger.info("checkBindCompany");
		Map<String, String> result = new HashMap<>();
		String openId = request.getParameter("openId");
		if (openId == null || openId.isEmpty()) {
			result.put(String.valueOf(WeixinJsonCode.NO_OPENID_ERROR_CODE),
					WeixinJsonCode.NO_OPENID_ERROR_MESSAGE);
		} else {
			result.put(String.valueOf(WeixinJsonCode.NO_ERROR_CODE),
					WeixinJsonCode.NO_ERROR_MESSAGE);
			Map<String, String> ret = settingService.checkCompanyBind(openId);
			if(ret.size() > 0) {
				result.putAll(ret);
				result.put(String.valueOf(WeixinJsonCode.USER_HAS_BIND_COMAPNY_CODE), 
						WeixinJsonCode.USER_HAS_BIND_COMAPNY_MESSAGE);
			} else {
				result.put(String.valueOf(WeixinJsonCode.USER_NOT_BIND_COMAPNY_CODE), 
						WeixinJsonCode.USER_NOT_BIND_COMAPNY_MESSAGE);
			}
		}
		logger.info("finishCheckBindCompany");
		logger.info("resultMap :{}", result.toString());
		return result;
	}
}
