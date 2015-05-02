package com.wisdom.weixin.service.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.utils.Base64Converter;
import com.wisdom.weixin.service.IExpenseAccountService;
import com.wisdom.weixin.utils.WeixinCache;

@Service("weixinExpenseAccountService")
public class ExpenseAccountServiceImpl implements IExpenseAccountService {

	private static final Logger logger = LoggerFactory
			.getLogger(ExpenseAccountServiceImpl.class);

	@Autowired
	private IInvoiceService invoiceService;

	@Autowired
	private IUserService userService;

	@Override
	public Map<String, List<Map<String, String>>> getBillsByOpenId(String openId) {
		Map<String, List<Map<String, String>>> retMap = null;
		String userId = userService.getUserIdByOpenId(openId);
		logger.debug("userId : {}", userId);
		if (userId != null && !userId.isEmpty()) {
			retMap = invoiceService.getBillsList(userId);
		}
		return retMap;
	}

	public String downloadFromUrl(String mediaId, String openId) {
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
			String userId = userService.getUserIdByOpenId(openId);
			logger.debug("userId : {}", userId);
			if (userId != null && !userId.isEmpty()) {
				Map<String, Object> retMap = invoiceService
						.createInvoiceProcess(userId, base64ImageStr, "0", "1");
				if (!retMap.containsKey("success")
						|| !(boolean) retMap.get("success")) {
					base64ImageStr = "";
				}
			} else {
				base64ImageStr = "";
			}
		} catch (Exception e) {
			logger.debug("Failed in download file.Exception : {}", e.toString());
		}
		return base64ImageStr;
	}

	@Override
	public boolean approvalBill(String approvalId, String invoiceId, String userId) {
		boolean status = false;
		Map<String, Object> retMap = invoiceService.excuteApproval(userId, approvalId, invoiceId);
		if(retMap.containsKey("success") && (boolean) retMap.get("success")) {
			status = true;
		}
		return status;
	}

}
