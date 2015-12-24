package com.wisdom.weixin.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.user.service.IUserService;
import com.wisdom.weixin.service.IExpenseAccountService;
import com.wisdom.weixin.service.IWeixinPushService;
import com.wisdom.weixin.utils.SubmitAuditBillResultEntity;
import com.wisdom.weixin.utils.SubmitAuditBillResultEntityWrapper;
import com.wisdom.weixin.utils.SubmitBillEntity;
import com.wisdom.weixin.utils.SubmitBillEntityWrapper;
import com.wisdom.weixin.utils.UploadBillEntity;
import com.wisdom.weixin.utils.UploadBillEntityWrapper;

@Controller
public class ExpenseAccountController {

	private static final Logger logger = LoggerFactory
			.getLogger(ExpenseAccountController.class);
	
	@Autowired
	private IExpenseAccountService expenseAccounterService;
	@Autowired
	private IWeixinPushService weixinPushService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IInvoiceService invoiceService;
	
	@RequestMapping(value="/downloadUserBill", method=RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public Map<String, String> downloadUserBill(@RequestBody UploadBillEntityWrapper wrapper, HttpServletRequest request) {
		String openId = request.getParameter("openId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		logger.debug("openId realPath : {}, {}", openId, realPath);
		Map<String, String> retMap = new HashMap<>();
		if(openId == null || openId.isEmpty()) {
			retMap.put("error_code", "3");
			retMap.put("error_message", "无法获取您微信的Openid，请稍后重新进入！");
		}
		List<UploadBillEntity> ube = wrapper.getUploadBillEntities();
		logger.debug("UploadBillEntity size : {}", ube.size());
		if(ube == null || ube.size() == 0) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "上传失败，服务器获取道德列表为空！");
			retMap.put("upload_count", "0");
		} else {
			int sum = 0;
			StringBuffer sb = new StringBuffer();
			for(UploadBillEntity up : ube) {
				logger.debug("mediaId : {}", up.getMediaId());
				logger.debug("amount : {}", up.getAmount());
				logger.debug("expenseTypeName : {}", up.getExpenseTypeName());
				logger.debug("expenseTypeId : {}", up.getExpenseTypeId());
				Map<String, Object> params = new HashMap<>();
				params.put("openId", openId);
				params.put("realPath", realPath);
				params.put("mediaId", up.getMediaId());
				params.put("expenseTypeId", Integer.valueOf(up.getExpenseTypeId()));
				params.put("amount", Double.valueOf(up.getAmount()));
				String base64ImageStr = expenseAccounterService.downloadFromUrl(params);
				if (!base64ImageStr.isEmpty()) {
					sum++;
					sb.append(up.getId());
					sb.append(" ");
				}
			}
			if(sum != ube.size()) {
				retMap.put("error_code", "2");
				retMap.put("error_message", "您选择上传的发票只有部分上传成功，请稍后重试！");
				retMap.put("upload_count", String.valueOf(sum) + "/" + String.valueOf(ube.size()));
			} else {
				retMap.put("error_code", "0");
				retMap.put("error_message", "全部上传成功！");
			}
			logger.debug("IDS : {}", sb.toString());
			if(sb.length() > 0) {
				retMap.put("ids", sb.toString().substring(0, sb.length() - 1));
			}
		}
		logger.debug("retMap : {}", retMap.toString());
		return retMap;
	}
	
	@RequestMapping("/uploadPersonInvoice")
	@ResponseBody
	public Map<String, String> uploadPersonInvoice(
			@RequestParam MultipartFile[] files,
			HttpServletRequest request) {
		logger.debug("uploadPersonInvoice");
		String openId = request.getParameter("openId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		logger.debug("openId realPath : {}, {}", openId, realPath);
		Map<String, String> retMap = new HashMap<>();
		if(openId == null || openId.isEmpty()) {
			retMap.put("error_code", "3");
			retMap.put("error_message", "无法获取您微信的Openid，请稍后重新进入！");
			return retMap;
		}
		String userId = userService.getUserIdByOpenId(openId);
		if(userId == null || userId.isEmpty()) {
			retMap.put("error_code", "30");
			retMap.put("error_message", "无法获取您的用户ID，请稍后重新进入！");
			return retMap;
		}
		if (files != null) {
			for(MultipartFile file:files) {
				String fileName = openId
						+ String.valueOf(System.currentTimeMillis()) + ".jpg";
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(),
							new File(realPath, fileName));
					invoiceService.createInvoiceProcess(userId, fileName, "0", "1", new HashMap<>());
				} catch (IOException e) {
					logger.debug(e.toString());
				}
			}
		}
		retMap.put("error_code", "0");
		retMap.put("error_message", "");
		return retMap;
	}

	@RequestMapping(value="/approvalBill", method=RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public Map<String, String> approvalBill(
			@RequestBody SubmitAuditBillResultEntityWrapper wrapper, HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String openId = request.getParameter("openId");
		logger.debug("openid : {}", openId);
		if(openId == null || openId.isEmpty()) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "无法获取您微信的Openid，请稍后重新进入！");
		}
		List<SubmitAuditBillResultEntity> sabr = wrapper.getSubmitAuditBillResultEntities();
		if(sabr == null || sabr.size() == 0) {
			retMap.put("error_code", "2");
			retMap.put("error_message", "提交失败，服务器接收到的审批列表为空，请检查！");
			logger.debug("auditList is null or size is 0");
		}
		int sum = 0;
		StringBuffer sb = new StringBuffer();
		Map<String, String> auditProcess = new HashMap<>();
		for(SubmitAuditBillResultEntity bill : sabr) {
			logger.debug("SubmitAuditBillResultEntity : {}", bill.toString());
			String userId = bill.getUser_id();
			String approvalId = bill.getApproval_id();
			String invoiceId = bill.getInvoice_id();
			int status = Integer.valueOf(bill.getApproval_status());
			if(expenseAccounterService.approvalBill(approvalId, invoiceId, userId,
					status, "")) {
				sum++;
				sb.append(bill.getInvoice_id());
				sb.append(" ");
				if(!auditProcess.containsKey(userId)) {
					auditProcess.put(userId, "");
					String toUser = userService.getOpenIdByUserId(userId);
					String auditName = userService.getUserNameByUserId(approvalId);
					String msgBody = "您的发票已被审核人"+ auditName +"审核，<a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx333ea15ba860f932&redirect_uri=http%3a%2f%2fwww.bangbangzhang.com%2fgetOpenIdRedirect%3fview%3dinbox.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect'>点此查看审核结果！</a>";
					weixinPushService.pushTextMessage(toUser, msgBody);
				}
			}
		}
		if(sum != sabr.size()) {
			retMap.put("error_code", "3");
			retMap.put("error_message", "您提交的审核结果只有部分提交成功，请稍后重试！");
			retMap.put("submit_count", String.valueOf(sum) + "/" + String.valueOf(sabr.size()));
		} else {
			retMap.put("error_code", "0");
			retMap.put("error_message", "全部提交成功！");
		}	
		logger.debug("IDS : {}", sb.toString());
		if(sb.length() > 0) {
			retMap.put("ids", sb.toString().substring(0, sb.length() - 1));
		}
		logger.debug("approvalBill result : {}", retMap.toString());
		return retMap;
	}
	
	@RequestMapping(value="/newApprovalBill")
	@ResponseBody
	public Map<String, String> newApprovalBill(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String openId = request.getParameter("openId");
		logger.debug("openid : {}", openId);
		if(openId == null || openId.isEmpty()) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "无法获取您微信的Openid，请稍后重新进入！");
			return retMap;
		}
		String invoiceIdString = request.getParameter("invoiceId");
		String approvalStatus = request.getParameter("approvalStatus");
		String[] invoiceIds = invoiceIdString.split(",");
		for(String invoiceId : invoiceIds) {
			expenseAccounterService.newApprovalBill(invoiceId, approvalStatus, "");
		}
		retMap.put("error_code", "0");
		retMap.put("error_message", "");
		logger.debug("newApprovalBill result : {}", retMap.toString());
		return retMap;
	}

	@RequestMapping("/getNeedAuditBills")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getMyBills(
			HttpServletRequest request) {
		String openId = request.getParameter("openId");
		Map<String, List<Map<String, Object>>> retMap = expenseAccounterService
				.getNeedAuditBillsByOpenId(openId);
		logger.debug("getNeedAuditBills result : {}", retMap.toString());
		return retMap;
	}
	
	@RequestMapping("/newGetNeedAuditBills")
	@ResponseBody
	public List<Map<String, Object>> newGetNeedAuditBills(
			HttpServletRequest request) {
		String openId = request.getParameter("openId");
		List<Map<String, Object>> retMap = expenseAccounterService
				.newGetNeedAuditBillsByOpenId(openId);
		logger.debug("newGetNeedAuditBills result : {}", retMap.toString());
		return retMap;
	}
	
	@RequestMapping("/getInboxBills")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getMyInbox(
			HttpServletRequest request) {
		String openId = request.getParameter("openId");
		Map<String, List<Map<String, Object>>> retMap = expenseAccounterService
				.getInboxBillsByOpenId(openId);
		logger.debug("getInboxBills result : {}", retMap.toString());
		return retMap;
	}
	
	@RequestMapping("/getWaitAuditInvoices")
	@ResponseBody
	public List<Map<String, Object>> getWaitAuditInvoices(
			HttpServletRequest request) {
		String openId = request.getParameter("openId");
		List<Map<String, Object>> ret = expenseAccounterService
				.getWaitAuditInvoices(openId);
		logger.debug("getWaitAuditInvoices result : {}", ret.toString());
		return ret;
	}
	
	@RequestMapping("/getFinishAuditInvoices")
	@ResponseBody
	public List<Map<String, Object>> getFinishAuditInvoices(
			HttpServletRequest request) {
		String openId = request.getParameter("openId");
		List<Map<String, Object>> ret = expenseAccounterService
				.getFinishAuditInvoices(openId);
		logger.debug("getFinishAuditInvoices result : {}", ret.toString());
		return ret;
	}
	
	@RequestMapping("/getAllExpenseType")
	@ResponseBody
	public List<Map<String, String>> getAllExpenseType(
			HttpServletRequest request) {
		List<Map<String, String>> retList = expenseAccounterService.getAllExpenseType();
		logger.debug("getAllExpenseType result : {}", retList.toString());
		return retList;
	}
	
	@RequestMapping(value="/submitBillListAudit", method=RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public Map<String, String> submitBillListAudit(
			@RequestBody SubmitBillEntityWrapper wrapper, HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String openId = request.getParameter("openId");
		logger.debug("openid : {}", openId);
		if(openId == null || openId.isEmpty()) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "无法获取您微信的Openid，请稍后重新进入！");
		}
		List<SubmitBillEntity> sbe = wrapper.getSubmitBillEntities();
		if(sbe == null || sbe.size() == 0) {
			retMap.put("error_code", "2");
			retMap.put("error_message", "提交失败，服务器接收到的申请列表为空，请检查！");
			logger.debug("invoiceIdList is null or size is 0");
		}
		int sum = 0;
		StringBuffer sb = new StringBuffer();
		for(SubmitBillEntity bill : sbe) {
			Map<String, String> params = new HashMap<>();
			params.put("invoiceId", bill.getInvoice_id());
			params.put("amount", bill.getBill_amount());
			params.put("expenseTypeId", bill.getBill_expenseTypeId());
			if(expenseAccounterService.submitBillAudit(openId, bill.getInvoice_id(), params)) {
				sum++;
				sb.append(bill.getInvoice_id());
				sb.append(" ");
			}
		}
		if(sum != sbe.size()) {
			retMap.put("error_code", "3");
			retMap.put("error_message", "您提交的审核申请只有部分提交成功，请稍后重试！");
			retMap.put("submit_count", String.valueOf(sum) + "/" + String.valueOf(sbe.size()));
		} else {
			retMap.put("error_code", "0");
			retMap.put("error_message", "全部提交成功！");
		}
		logger.debug("IDS : {}", sb.toString());
		if(sb.length() > 0) {
			retMap.put("ids", sb.toString().substring(0, sb.length() - 1));
		}
		logger.debug("submitBillListAudit result : {}", retMap.toString());
		return retMap;
	}

}