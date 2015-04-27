package com.wisdom.weixin.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wisdom.weixin.service.ITokenCheckService;
import com.wisdom.weixin.utils.WeixinConstEnum;
import com.wisdom.weixin.utils.WeixinMessageDigestUtil;

public class TokenCheckServiceImpl implements ITokenCheckService{

	private static final Logger logger = LoggerFactory
			.getLogger(TokenCheckServiceImpl.class);
	
	@Override
	public String tokenCheck(String signature, String echostr, String timestamp, String nonce) {
		String[] str = { WeixinConstEnum.TOKEN.toString(), timestamp, nonce };
		Arrays.sort(str);
		String bigStr = str[0] + str[1] + str[2];
		String pwd = WeixinMessageDigestUtil.getInstance().encipher(bigStr);
		logger.debug("token check pwd : {}", pwd);
		if (pwd.equals(signature)) {
			logger.debug("weixin token pass.");
			return echostr;
		} else {
			logger.debug("weixin token fail.");
			return "";
		}
	}

}
