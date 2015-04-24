package com.wisdom.weixin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.wisdom.weixin.service.IMessageProcessService;
import com.wisdom.weixin.service.ITokenCheckService;
import com.wisdom.weixin.service.impl.MessageProcessServiceIMpl;
import com.wisdom.weixin.service.impl.TokenCheckServiceImpl;
import com.wisdom.weixin.utils.WeixinTools;

@Controller
@SessionAttributes({ "userOpenId" })
public class CommonController {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonController.class);

	private ITokenCheckService tokenCheckService = new TokenCheckServiceImpl();

	private IMessageProcessService msgProcessService = new MessageProcessServiceIMpl();

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
			ret = msgProcessService.processWeixinMessage(request);
		}
		logger.debug("finish a weixin request");
		return ret;
	}
	
	@RequestMapping("/getJsConfigInfo")
	@ResponseBody
	public Map<String, String> getJsConfigInfo(Model model,
			HttpServletRequest request) {
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
	public ModelAndView getOpenIdRedirect(Model model,
			HttpServletRequest request) {
		logger.debug("getOpenIdRedirect");
		String code = request.getParameter("code");
		String view = request.getParameter("view");
		String openId = "";
		if(code == null || code.isEmpty()) {
			model.addAttribute("userOpenId", "");
		} else {
			openId = WeixinTools.getOpenId(code);
			model.addAttribute("userOpenId", openId == null || openId.isEmpty() ? "" : openId);
		}
		logger.debug("finishGetOpenIdRedirect");
		logger.debug("code :{}, openId :{}, view :{}", code, openId, view);
		return new ModelAndView(view);
	}
	
	@RequestMapping("/getUserOpenId")
	@ResponseBody
	public Map<String, String> getUserOpenId(Model model,
			HttpServletRequest request) {
		logger.debug("getUserOpenId");
		Map<String, String> result = new HashMap<>();
		result.put("openId", "");
		if(model.asMap().containsKey("userOpenId")) {
			result.put("openId", (String)model.asMap().get("userOpenId"));
		}
		logger.debug("finishGetUserOpenId");
		logger.debug("resultMap :{}", result.toString());
		return result;
	}
}
