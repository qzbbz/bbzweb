package com.wisdom.weixin.service.impl;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.company.service.IExpenseTypeService;
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
	
	@Autowired
	private IExpenseTypeService expenseTypeService;

	@Override
	public Map<String, List<Map<String, Object>>> getBillsByOpenId(String openId) {
		Map<String, List<Map<String, Object>>> retMap = null;
		String userId = userService.getUserIdByOpenId(openId);
		logger.debug("userId : {}", userId);
		if (userId != null && !userId.isEmpty()) {
			retMap = invoiceService.getBillsList(userId);
		}
		return retMap;
	}
	
	@Override
	public Map<String, List<Map<String, Object>>> getInboxByOpenId(String openId) {
		Map<String, List<Map<String, Object>>> retMap = null;
		String userId = userService.getUserIdByOpenId(openId);
		logger.debug("userId : {}", userId);
		if (userId != null && !userId.isEmpty()) {
			retMap = invoiceService.getNeededAuditBillList(userId);
		}
		return retMap;
	}

	
	public String downloadFromUrl(String mediaId, String openId, String realPath) {
		String base64ImageStr = "";
		String weixinFileURL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		weixinFileURL = weixinFileURL.replace("ACCESS_TOKEN",
				WeixinCache.getAccessToken()).replace("MEDIA_ID", mediaId);
		try {
			URL realUrl = new URL(weixinFileURL);
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			conn.setConnectTimeout(250000);
			conn.setReadTimeout(250000);
			conn.connect();
			InputStream in = conn.getInputStream();
			String fileName = openId + String.valueOf(System.currentTimeMillis()) + ".jpg";
			FileUtils.copyInputStreamToFile(in, new File(realPath, fileName));
			base64ImageStr = fileName;
			logger.debug("uploadBillFilePath : {}", base64ImageStr);
			String userId = userService.getUserIdByOpenId(openId);
			logger.debug("userId : {}", userId);
			if (userId != null && !userId.isEmpty()) {
				Map<String, Object> retMap = invoiceService
						.createInvoiceProcess(userId, base64ImageStr, "0", "1",new HashMap());
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
	
	
	public String downloadFromUrl(String mediaId, String openId) {
		String base64ImageStr = "";
		String weixinFileURL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		weixinFileURL = weixinFileURL.replace("ACCESS_TOKEN",
				WeixinCache.getAccessToken()).replace("MEDIA_ID", mediaId);
		try {
			URL realUrl = new URL(weixinFileURL);
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			conn.setConnectTimeout(250000);
			conn.setReadTimeout(250000);
			conn.connect();
			InputStream in = conn.getInputStream();
			base64ImageStr = Base64Converter.imageToBase64(in);
			in.close();
			String userId = userService.getUserIdByOpenId(openId);
			logger.debug("userId : {}", userId);
			if (userId != null && !userId.isEmpty()) {
				Map<String, Object> retMap = invoiceService
						.createInvoiceProcess(userId, base64ImageStr, "0", "1",new HashMap());
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
	public boolean approvalBill(String approvalId, String invoiceId, String userId, int approval_status) {
		boolean status = false;
		Map<String, Object> retMap = invoiceService.excuteApproval(userId, approvalId, invoiceId, approval_status);
		if(retMap.containsKey("success") && (boolean) retMap.get("success")) {
			status = true;
		}
		return status;
	}

	@Override
	public boolean submitExpenseAccount(String openId, String image) {
		String userId = userService.getUserIdByOpenId(openId);
		if(userId == null || userId.isEmpty()) return false;
		Map<String, Object> retMap = invoiceService.createInvoiceProcess(userId, image, "0", "1",new HashMap());
		if(retMap.containsKey("success") && (boolean)retMap.get("success")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Map<Long, String> getAllExpenseType() {
		return expenseTypeService.getExpenseTypeMap();
	}

}
