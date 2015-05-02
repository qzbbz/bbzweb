package com.wisdom.weixin.service;

public interface IWeixinPushService {

	public boolean pushTextMessage(String openId, String message);
	
}
