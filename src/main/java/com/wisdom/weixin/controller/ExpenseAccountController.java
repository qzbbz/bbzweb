package com.wisdom.weixin.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.wisdom.common.model.Dispatcher;
import com.wisdom.common.model.InvoiceApproval;
import com.wisdom.common.model.TestInvoiceRecord;
import com.wisdom.common.model.UserInvoice;
import com.wisdom.dispatch.service.IDispatcherService;
import com.wisdom.invoice.dao.IInvoiceDao;
import com.wisdom.invoice.service.IInvoiceApprovalService;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.invoice.service.IUserInvoiceService;
import com.wisdom.invoice.service.impl.InvoiceApprovalServiceImpl;
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
	@Autowired
	private IInvoiceApprovalService invoiceApprovalService;
	@Autowired
	private IDispatcherService dispatcherService;
	@Autowired
	private IUserInvoiceService userInvoiceService;
	@Autowired
	private IInvoiceDao invoiceDao;
	
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
		String expenseAmountStr = request.getParameter("expense_amount");
		String expenseTypeStr = request.getParameter("expense_type");
		expenseAmountStr = expenseAmountStr.substring(0, expenseAmountStr.length() - 1);
		expenseTypeStr = expenseTypeStr.substring(0, expenseTypeStr.length() - 1);
		String[] expenseAmounts = expenseAmountStr.split(";");
		String[] expenseTypes = expenseTypeStr.split(";");
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
			Integer count = 0;
			for(MultipartFile file:files) {
				String fileName = openId
						+ String.valueOf(System.currentTimeMillis()) + ".jpg";
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(),
							new File(realPath, fileName));
					Map<String, Object> param = new HashMap<>();
					param.put("amount", expenseAmounts[count]);
					param.put("type", expenseTypes[count]);
					invoiceService.createInvoiceProcess(userId, fileName, "0", "1", param, "wechat");
					count = count + 1;
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
	
	@RequestMapping("/newGetNeedAuditBillsSummary")
	@ResponseBody
	public List<Map<String, Object>> newGetNeedAuditBillsSummary(
			HttpServletRequest request) {
		String openId = request.getParameter("openId");
		List<Map<String, Object>> results = expenseAccounterService
				.newGetNeedAuditBillsByOpenId(openId);
		System.out.println(results);
		//System.out.println(retMap);
		List<Map<String, Object>> retList = new ArrayList<>();
		String id_list_string = "";
		for(Map<String, Object> result: results){
			Map<String, Object> element = new HashMap<>();
			element.put("invoice_total_amount", result.get("invoice_total_amount"));
			element.put("user_name", result.get("user_name"));
			element.put("inovice_count", result.get("invoice_count"));
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("list");
			for(Map<String, Object> detail: list){
				id_list_string += detail.get("invoice_id")+",";
			}
			id_list_string = id_list_string.substring(0, id_list_string.length()-1);
			element.put("person_invoice_count", list.size());
			element.put("invoice_id_list_string", id_list_string);
			
			retList.add(element);
		}
		logger.debug("newGetNeedAuditBillsSummary result : {}", retList.toString());
		return retList;
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
	
	@RequestMapping("/storeRequestInvoiceIds")
	@ResponseBody
	public Map<String, String> storeRequestInvoiceIds(
			HttpServletRequest request) {
		String invoiceIds = request.getParameter("invoice_ids");
		request.getSession().setAttribute("requestInvoiceIds", invoiceIds);
		Map<String, String> retMap = new HashMap<>();
		retMap.put("status", "ok");
		return retMap;
	}
	
	@RequestMapping("/getInovicesByIds")
	@ResponseBody
	public Map<String, Object> getInovicesByIds(
			HttpServletRequest request) {
		String invoiceIds = (String) request.getSession().getAttribute("requestInvoiceIds");
		String openId = request.getParameter("open_id");
		String approvalId = userService.getUserIdByOpenId(openId);
		List<InvoiceApproval> invoiceApprovalList = invoiceApprovalService.getInvoiceApprovalListByInvoiceIds(invoiceIds);
		List<Map<String, Object>> resultList = new ArrayList<>();
		String submitUserName = "";
		 Map<String, Object> retMap = new HashMap<>();
		 Map<String, Map<String, Object>> abstractInfoMap = new HashMap<>();
			Map<String, List<Map<String, Object>>> detailInfoMap = new HashMap<>();
			Double invoiceTotalAmount = (double) 0;
			for (InvoiceApproval invoiceApproval : invoiceApprovalList) {
				if (invoiceApproval.getStatus() != 0)
					continue;
				logger.debug("invoiceApproval, invoice_id :{}", invoiceApproval.getInvoiceId());
				Dispatcher dispatch = dispatcherService.getDispatcherByInvoiceId(invoiceApproval.getInvoiceId());
				if (dispatch != null && -1 == dispatch.getStatus()) {
					continue;
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("processStatus", invoiceApproval.getStatus());
				map.put("invoice_id", invoiceApproval.getInvoiceId());
				map.put("approval_status", invoiceApproval.getApprovalStatus() == 0 ? true : false);
				map.put("approval_id", approvalId);
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Timestamp stamp = invoiceApproval.getUpdateTime();
				map.put("submit_time", sdf.format(stamp));
				UserInvoice userInvoice = userInvoiceService.getUserInvoiceByInvoiceId(invoiceApproval.getInvoiceId());
				if (null != userInvoice) {
					map.put("user_id", userInvoice.getUserId());
					stamp = userInvoice.getCreateTime();
					map.put("bill_date", sdf.format(stamp));
					String userName = userService.getUserNameByUserId(userInvoice.getUserId());
					submitUserName = userName;
					map.put("user_name", userName);
					map.put("bill_status", userInvoice.getStatus());
					String approvalName = userService.getUserNameByUserId(userInvoice.getUserId());
					map.put("approval_name", approvalName);
					map.put("reasons", userInvoice.getReasons() == null || userInvoice.getReasons().isEmpty() ? "无"
							: userInvoice.getReasons());
				}
				map.put("approval_id", approvalId);

				// Invoice invoice =
				// singleInvoiceService.getSingleInvoiceInfo(invoiceApproval.getInvoiceId());
				TestInvoiceRecord invoiceRecord = invoiceDao.getInvoiceRecordById(invoiceApproval.getInvoiceId());
				if (null == invoiceRecord) {
					logger.debug("invoice  record not exsisted, invoice_id : {}", invoiceApproval.getInvoiceId());
					// return map;
					continue;
				}
				map.put("bill_title", invoiceRecord.getType());
				map.put("bill_amount", invoiceRecord.getAmount());
				invoiceTotalAmount += invoiceRecord.getAmount();
				// map.put("bill_expenseTypeId", 5);
				map.put("desc", invoiceRecord.getType());
				map.put("bill_expenseTypeName", invoiceRecord.getSupplierName());
				map.put("bill_date", sdf.format(stamp));
				// Attachment attach =
				// attachmentService.getAttachMentByInvoiceId(invoice.getId());
				// if(null != attach){
				map.put("bill_img", "/files/company/" + invoiceRecord.getFileName());
				// }

				resultList.add(map);

				logger.debug("Get invoice record data");

			}
			retMap.put("list", resultList);
			retMap.put("invoice_count", resultList.size());
			retMap.put("invoice_total_amount", invoiceTotalAmount);
			retMap.put("user_name", submitUserName);
			return retMap;

	}

	@RequestMapping("/addCommentToInvoice")
	@ResponseBody
	public Map<String, String> addCommentToInvoice(
			HttpServletRequest request) {
		String invoiceId = request.getParameter("invoice_id");
		String comment = request.getParameter("comment");
		Map<String, String> retMap = new HashMap<>();
		invoiceService.setInvoiceComment(Long.parseLong(invoiceId), comment);
		retMap.put("status", "ok");
		return retMap;
	}
}