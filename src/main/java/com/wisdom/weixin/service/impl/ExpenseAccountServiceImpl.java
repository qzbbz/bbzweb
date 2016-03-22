package com.wisdom.weixin.service.impl;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.weixin.dao.IWeixinDao;
import com.weixin.dao.IWeixinUserWorkGoingOutDao;
import com.weixin.dao.IWeixinWorkGoingOutDao;
import com.weixin.model.WeixinUserWorkGoingOutModel;
import com.weixin.model.WeixinWorkGoingOutModel;
import com.wisdom.common.model.InvoiceApproval;
import com.wisdom.common.model.TestInvoice;
import com.wisdom.company.service.IDeptService;
import com.wisdom.company.service.IExpenseTypeService;
import com.wisdom.invoice.dao.IInvoiceApprovalDao;
import com.wisdom.invoice.domain.ProcessStatus;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.user.service.IUserDeptService;
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
	
	@Autowired
	private IDeptService deptService;
	
	@Autowired
	private IUserDeptService userDeptService;
	
	@Autowired
	private IInvoiceApprovalDao invoiceApprovalDao;
	
	@Autowired
	private IInvoiceApprovalDao userInvoiceService;
	
	@Autowired
	private IWeixinDao weixinDao;

	@Override
	public Map<String, List<Map<String, Object>>> getInboxBillsByOpenId(String openId) {
		Map<String, List<Map<String, Object>>> retMap = null;
		String userId = userService.getUserIdByOpenId(openId);
		logger.debug("userId : {}", userId);
		if (userId != null && !userId.isEmpty()) {
			retMap = invoiceService.getBillsList(userId);
		}
		logger.debug("retMap : {}", retMap.toString());
		return retMap;
	}
	
	@Override
	public List<Map<String, Object>> getWaitAuditInvoices(String openId) {
		List<Map<String, Object>> ret = null;
		String userId = userService.getUserIdByOpenId(openId);
		logger.debug("userId : {}", userId);
		if (userId != null && !userId.isEmpty()) {
			ret = invoiceService.getWaitAuditInvoices(userId);
		}
		logger.debug("ret : {}", ret.toString());
		return ret;
	}
	
	@Override
	public List<Map<String, Object>> getFinishAuditInvoices(String openId) {
		List<Map<String, Object>> ret = null;
		String userId = userService.getUserIdByOpenId(openId);
		logger.debug("userId : {}", userId);
		if (userId != null && !userId.isEmpty()) {
			ret = invoiceService.getFinishAuditInvoices(userId);
		}
		logger.debug("ret : {}", ret.toString());
		return ret;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getNeedAuditBillsByOpenId(String openId) {
		Map<String, List<Map<String, Object>>> retMap = null;
		String userId = userService.getUserIdByOpenId(openId);
		logger.debug("userId : {}", userId);
		if (userId != null && !userId.isEmpty()) {
			retMap = invoiceService.getNeededAuditBillList(userId);
		}
		logger.debug("retMap : {}", retMap.toString());
		return retMap;
	}
	
	@Override
	public List<Map<String, Object>> newGetNeedAuditBillsByOpenId(String openId) {
		List<Map<String, Object>> retMap = null;
		String userId = userService.getUserIdByOpenId(openId);
		logger.debug("userId : {}", userId);
		if (userId != null && !userId.isEmpty()) {
			retMap = invoiceService.newGetNeededAuditBillList(userId);
		}
		logger.debug("retMap : {}", retMap.toString());
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
			String fileName = openId
					+ String.valueOf(System.currentTimeMillis()) + ".jpg";
			FileUtils.copyInputStreamToFile(in, new File(realPath, fileName));
			base64ImageStr = fileName;
			logger.debug("uploadBillFilePath : {}", base64ImageStr);
			String userId = userService.getUserIdByOpenId(openId);
			logger.debug("userId : {}", userId);
			if (userId != null && !userId.isEmpty()) {
				Map<String, Object> params = new HashMap<>();
				// params.put("expenseTypeId", Integer.valueOf());
				// params.put("expenseTypeId", Double.valueOf());
				Map<String, Object> retMap = invoiceService
						.createInvoiceProcess(userId, base64ImageStr, "0", "1",
								new HashMap(), "wechat");
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
						.createInvoiceProcess(userId, base64ImageStr, "0", "1",
								new HashMap(), "wechat");
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
	public boolean approvalBill(String approvalId, String invoiceId,
			String userId, int approval_status, String reasons) {
		boolean status = false;
		Map<String, Object> retMap = invoiceService.excuteApproval(userId,
				approvalId, invoiceId, approval_status, reasons);
		if (retMap.containsKey("success") && (boolean) retMap.get("success")) {
			status = true;
		}
		return status;
	}
	
	@Override
	public boolean newApprovalBill(String invoiceId, String  approval_status, String reasons) {
		boolean status = false;
		int approvalstatus = Integer.valueOf(approval_status);
		InvoiceApproval ia = invoiceApprovalDao.getInvoiceApprovalByInvoiceId(Long.valueOf(invoiceId));
		String approvalUserId = ia.getUserId();
		logger.debug("newApprovalBill approvalUserId : {}", approvalUserId);
		String userId = userInvoiceService.getInvoiceApprovalByInvoiceId(Long.valueOf(invoiceId)).getUserId();
		logger.debug("newApprovalBill userId : {}", userId);
		Map<String, Object> retMap = invoiceService.excuteApproval(userId,
				approvalUserId, invoiceId, approvalstatus, reasons);
		if (retMap.containsKey("success") && (boolean) retMap.get("success")) {
			status = true;
		}
		return status;
	}
	
    @Override
    public boolean newApprovalWork(String workId, String approval_status, String reasons) {
        logger.debug("newApprovalWork workId : {}", workId);
        return weixinDao.updateUserWorkGoingOut(workId, approval_status, reasons);
       }
	@Override
	public boolean submitExpenseAccount(String openId, String image) {
		String userId = userService.getUserIdByOpenId(openId);
		if (userId == null || userId.isEmpty())
			return false;
		Map<String, Object> retMap = invoiceService.createInvoiceProcess(
				userId, image, "0", "1", new HashMap(), "wechat");
		if (retMap.containsKey("success") && (boolean) retMap.get("success")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Map<String, String>> getAllExpenseType() {
		return expenseTypeService.getExpenseTypeMap();
	}

	@Override
	public String downloadFromUrl(Map<String, Object> params) {
		String base64ImageStr = "";
		String weixinFileURL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		if (params == null || !params.containsKey("mediaId"))
			return base64ImageStr;
		String userId = userService.getUserIdByOpenId((String)params.get("openId"));
		long deptId = userDeptService.getDeptIdByUserId(userId);
		String costCenterCode = deptService.getCostCenterCodeById(deptId);
		params.put("costCenterCode", costCenterCode);
		weixinFileURL = weixinFileURL.replace("ACCESS_TOKEN",
				WeixinCache.getAccessToken()).replace("MEDIA_ID",
				(String) params.get("mediaId"));
		if (!params.containsKey("openId") || !params.containsKey("realPath"))
			return base64ImageStr;
		try {
			URL realUrl = new URL(weixinFileURL);
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			conn.setConnectTimeout(250000);
			conn.setReadTimeout(250000);
			conn.connect();
			InputStream in = conn.getInputStream();
			String fileName = (String) params.get("openId")
					+ String.valueOf(System.currentTimeMillis()) + ".jpg";
			FileUtils.copyInputStreamToFile(in, new File((String) params.get("realPath"), fileName));
			base64ImageStr = fileName;
			logger.debug("uploadBillFilePath : {}", base64ImageStr);
			logger.debug("userId : {}", userId);
			if (userId != null && !userId.isEmpty()) {
				Map<String, Object> retMap = invoiceService
						.createInvoiceProcess(userId, base64ImageStr, "0", "1",
								params, "wechat");
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
	public boolean submitBillAudit(String openId, String invoiceId, Map<String, String> params) {
		String userId = userService.getUserIdByOpenId(openId);
		logger.debug("user id : {}", userId);
		Map ret = invoiceService.submitUserInvoice(userId, Long.valueOf(invoiceId), params);
		logger.debug("retMap : {}", ret.toString());
		return ret == null || !ret.containsKey("success") || !(boolean)ret.get("success") ? false : true;
	}



  

}
