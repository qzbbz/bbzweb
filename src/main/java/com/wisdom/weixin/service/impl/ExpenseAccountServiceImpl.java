package com.wisdom.weixin.service.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.web.utils.Base64Converter;
import com.wisdom.weixin.service.IExpenseAccountService;
import com.wisdom.weixin.utils.WeixinCache;

@Service("weixinExpenseAccountService")
public class ExpenseAccountServiceImpl implements IExpenseAccountService {

	private static final Logger logger = LoggerFactory
			.getLogger(ExpenseAccountServiceImpl.class);
	
	@Autowired
	private IAttachmentService attachmenstService;
	
	@Override
	public Map<String, Map<String, String>> getBillsByOpenId(String openId) {
		Map<String, Map<String, String>> retMap = new HashMap<>();
		retMap.put("uploadedList", new HashMap<>());
		retMap.put("progressList", new HashMap<>());
		retMap.put("finishList", new HashMap<>());
		
		return retMap;
	}
	
	public String downloadFromUrl(String mediaId) {
		String base64ImageStr = "";
		String weixinFileURL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		weixinFileURL = weixinFileURL.replace("ACCESS_TOKEN",
				WeixinCache.getAccessToken()).replace("MEDIA_ID", mediaId);
		try {
			URL realUrl = new URL(weixinFileURL);
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			conn.setConnectTimeout(25000);
			conn.setReadTimeout(25000);
			conn.connect();
			InputStream in = conn.getInputStream();
			base64ImageStr = Base64Converter.imageToBase64(in);
			in.close();
		} catch (Exception e) {
			logger.debug("Failed in download file.Exception : {}", e.toString());
		}
		return base64ImageStr;
	}

}
