package com.wisdom.weixin.service;

public interface ITokenCheckService {

	public String tokenCheck(String signature, String echostr,
			String timestamp, String nonce);

}
