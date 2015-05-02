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
    public Map<String, String> userBindCompany(HttpServletRequest request) {
        logger.debug("userBindCompany");
        Map<String, String> result = new HashMap<>();
        String openId = request.getParameter("openId");
        String inviteCode = request.getParameter("inviteCode");
        if (openId == null || openId.isEmpty() || inviteCode == null || inviteCode.isEmpty()) {
            result.put("error_code", String.valueOf(WeixinJsonCode.NO_OPENID_OR_INVITE_CODE_ERROR_CODE));
            result.put("error_message", WeixinJsonCode.NO_OPENID_OR_INVITE_CODE_ERROR_MESSAGE);
        } else {
        	logger.debug("call settingService");
        	result.put("error_code", String.valueOf(WeixinJsonCode.NO_ERROR_CODE));
            result.put("error_message", WeixinJsonCode.NO_ERROR_MESSAGE);
            Map<String, String> ret = settingService.userBindCompany(openId, inviteCode);
            result.putAll(ret);
        }
        logger.debug("finishUserBindCompany");
        logger.debug("resultMap :{}", result.toString());
        return result;
    }
}