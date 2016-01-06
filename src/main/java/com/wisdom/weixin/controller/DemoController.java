package com.wisdom.weixin.controller;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.weixin.utils.UploadBillEntity;
import com.wisdom.weixin.utils.UploadBillEntityWrapper;
import com.wisdom.weixin.utils.WeixinCache;

@Controller
public class DemoController {

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private IInvoiceService invoiceService;

	@RequestMapping(value = "/demoDownloadUserBill", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Map<String, String> downloadUserBill(@RequestBody UploadBillEntityWrapper wrapper,
			HttpServletRequest request) {
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/demo");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/demo";
		Map<String, String> retMap = new HashMap<>();
		List<UploadBillEntity> ube = wrapper.getUploadBillEntities();
		logger.debug("UploadBillEntity size : {}", ube.size());
		if (ube == null || ube.size() == 0) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "上传失败，服务器获取道德列表为空！");
			retMap.put("upload_count", "0");
		} else {
			int sum = 0;
			StringBuffer sb = new StringBuffer();
			for (UploadBillEntity up : ube) {
				logger.debug("mediaId : {}", up.getMediaId());
				String base64ImageStr = downloadFromUrl(up.getMediaId(), realPath);
				if (!base64ImageStr.isEmpty()) {
					sum++;
					sb.append(up.getId());
					sb.append(" ");
				}
			}
			if (sum != ube.size()) {
				retMap.put("error_code", "2");
				retMap.put("error_message", "您选择上传的发票只有部分上传成功，请稍后重试！");
				retMap.put("upload_count", String.valueOf(sum) + "/" + String.valueOf(ube.size()));
			} else {
				retMap.put("error_code", "0");
				retMap.put("error_message", "全部上传成功！");
			}
			logger.debug("IDS : {}", sb.toString());
			if (sb.length() > 0) {
				retMap.put("ids", sb.toString().substring(0, sb.length() - 1));
			}
		}
		logger.debug("retMap : {}", retMap.toString());
		return retMap;
	}

	private String downloadFromUrl(String mediaId, String realPath) {
		String base64ImageStr = "";
		String weixinFileURL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		weixinFileURL = weixinFileURL.replace("ACCESS_TOKEN", WeixinCache.getAccessToken()).replace("MEDIA_ID",
				mediaId);
		try {
			URL realUrl = new URL(weixinFileURL);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			conn.setConnectTimeout(250000);
			conn.setReadTimeout(250000);
			conn.connect();
			InputStream in = conn.getInputStream();
			String fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
			FileUtils.copyInputStreamToFile(in, new File(realPath, fileName));
			base64ImageStr = fileName;
			logger.debug("uploadDemoFilePath : {}", base64ImageStr);
			Map<String, Object> retMap = invoiceService.createInvoiceProcess("jzzbeyond@163.com", base64ImageStr, "0", "1",
					new HashMap<>(), "wechat");
			if (!retMap.containsKey("success") || !(boolean) retMap.get("success")) {
				base64ImageStr = "";
			}

		} catch (Exception e) {
			logger.debug("Failed in download file.Exception : {}", e.toString());
		}
		return base64ImageStr;
	}
}
