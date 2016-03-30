package com.wisdom.weixin.controller;


import com.wisdom.weixin.service.ISettingService;
import com.wisdom.weixin.utils.WeixinJsonCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SettingController {

    private static final Logger logger = LoggerFactory
            .getLogger(SettingController.class);

    @Autowired
    private ISettingService settingService;

    @RequestMapping("/userBindCompany")
    @ResponseBody
    public Map<String, Object> userBindCompany(HttpServletRequest request) {
        logger.debug("userBindCompany");
        Map<String, Object> result = new HashMap<>();
        String openId = request.getParameter("openId");
        String inviteCode = request.getParameter("inviteCode");
        String name = request.getParameter("name") == null ? "" : request.getParameter("name");
        String email = request.getParameter("email") == null ? "" : request.getParameter("email");
        if (openId == null || openId.isEmpty() || inviteCode == null || inviteCode.isEmpty()) {
            result.put("error_code", String.valueOf(WeixinJsonCode.NO_OPENID_OR_INVITE_CODE_ERROR_CODE));
            result.put("error_message", WeixinJsonCode.NO_OPENID_OR_INVITE_CODE_ERROR_MESSAGE);
        } else {
        	logger.debug("call settingService");
        	result.put("error_code", String.valueOf(WeixinJsonCode.NO_ERROR_CODE));
            result.put("error_message", WeixinJsonCode.NO_ERROR_MESSAGE);
            Map<String, Object> ret = settingService.userBindCompany(openId, inviteCode);
            settingService.updateUserInfo(openId, name, email);
            result.putAll(ret);
            if(result.get("userName") == null || ((String)result.get("userName")).isEmpty()) {
            	result.put("userName", name);
            }
            if(result.get("userMsgEmail") == null || ((String)result.get("userMsgEmail")).isEmpty()) {
            	result.put("userMsgEmail", email);
            }
        }
        logger.debug("finishUserBindCompany");
        logger.debug("resultMap :{}", result.toString());
        return result;
    }
    
    @RequestMapping("/userDisbindCompany")
    @ResponseBody
    public Map<String, String> userDisbindCompany(HttpServletRequest request) {
        logger.debug("userDisbindCompany");
        Map<String, String> result = new HashMap<>();
        String openId = request.getParameter("openId");
        if (openId == null || openId.isEmpty()) {
            result.put("error_code", "1");
        } else {
        	logger.debug("call settingService");
            if(settingService.userDisbindCompany(openId)) {
            	result.put("error_code", "0");
            } else {
            	result.put("error_code", "1");
            }
        }
        logger.debug("finishUserDisbindCompany");
        logger.debug("resultMap :{}", result.toString());
        return result;
    }
    
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public Map<String, String> updateUserInfo(HttpServletRequest request) {
        logger.debug("updateUserInfo");
        Map<String, String> result = new HashMap<>();
        String openId = request.getParameter("openId");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        logger.debug("updateUserInfo email : {}", email);
        if (openId == null || openId.isEmpty()){
            result.put("error_code", "1");
            result.put("error_message", "无法获取您的微信Openid，请稍后重试!");
        } else if(name == null || name.isEmpty() ||
        		email == null || email.isEmpty()) {
        	result.put("error_code", "1");
            result.put("error_message", "用户姓名或电子邮件地址为空，请检查！");
        } else {
        	Map<String, String> ret = settingService.updateUserInfo(openId, name, email);
            if(ret.get("error_code") != "0") {
            	result.put("error_code", "1");
                result.put("error_message", ret.get("error_message"));
            } else {
            	result.put("error_code", "0");
                result.put("error_message", "更新信息成功！");
                result.put("userName", name);
                result.put("userMsgEmail", email);
            }
        }
        logger.debug("finishUpdateUserInfo");
        logger.debug("resultMap :{}", result.toString());
        return result;
    }
}