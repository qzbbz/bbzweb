package com.wisdom.web.api.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.common.model.Invoice;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.company.service.IDeptService;
import com.wisdom.company.service.IExpenseTypeService;
import com.wisdom.invoice.service.IAttachmentService;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.invoice.service.ISingleInvoiceService;
import com.wisdom.user.service.IUserDeptService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.ICompanyUserApi;

@Service("companyUserService")
public class CompanyUserApiImpl implements ICompanyUserApi {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyUserApiImpl.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private IDeptService deptService;

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IUserDeptService userDeptService;

	@Autowired
	private IInvoiceService invoiceService;

	@Autowired
	private ISingleInvoiceService singleInvoiceService;

	@Autowired
	private IExpenseTypeService expenseTypeService;

	@Autowired
	private IAttachmentService attachmentService;

	@Override
	public Map<String, String> uploadCompanyUserBill(
			Map<String, String> params, MultipartFile file) {
		Map<String, String> retMap = new HashMap<>();
		try {
			String userId = params.get("userId");
			long deptId = userDeptService.getDeptIdByUserId(userId);
			String costCenterCode = deptService.getCostCenterCodeById(deptId);
			String fileName = getGernarateFileName(file, userId);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					params.get("realPath"), fileName));
			Map<String, Object> invoiceRetMap = invoiceService
					.createDraftInvoice(userId, "/img/billImg/" + fileName,
							costCenterCode);
			if (invoiceRetMap != null && (boolean) invoiceRetMap.get("success")) {
				retMap.put("error_code", "0");
			} else {
				retMap.put("error_code", "1");
				retMap.put("error_message", "上传发票失败，请稍后重试！");
			}
		} catch (IOException e) {
			logger.debug("uploadCompanyUserBill exception : {}", e.toString());
			retMap.put("error_code", "2");
			retMap.put("error_message", "上传发票失败，请稍后重试！");
		}
		return retMap;
	}

	private String getGernarateFileName(MultipartFile file, String userId) {
		Random rdm = new Random(System.currentTimeMillis());
		String extendName = file.getOriginalFilename().substring(
				file.getOriginalFilename().indexOf(".") + 1);
		return userId + System.currentTimeMillis() + Math.abs(rdm.nextInt())
				% 1000 + (extendName == null ? ".unknown" : "." + extendName);
	}

	@Override
	public List<Map<String, String>> getCompanyUserInvoiceByStatus(
			Map<String, String> params) {
		List<Map<String, String>> retList = new ArrayList<>();
		String status = params.get("status");
		String page = params.get("page");
		String pageSize = params.get("pageSize");
		String userId = params.get("userId");
		if (StringUtils.isEmpty(status) || StringUtils.isEmpty(userId)) {
			logger.debug("params are wrong!");
			return retList;
		}
		List<Invoice> list = new ArrayList<Invoice>();
		if ("1".equals(status)) {// 草稿状态
			list = singleInvoiceService.getUserInvoiceByStatus(userId, status);
		} else {// 提交状态
			int iPage = 1;
			int iPageSize = 10;
			try {
				iPage = Integer.parseInt(page);
				iPageSize = Integer.parseInt(pageSize);
			} catch (Exception e) {
				logger.error("parse page pagesize error, exception : {}",
						e.toString());
				iPage = 1;
				iPageSize = 10;
			}
			list = singleInvoiceService.getUserInvoiceByStatusByPage(userId,
					status, iPage, iPageSize);
		}
		if (list == null || list.size() == 0)
			return null;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Invoice invoice : list) {
			Map<String, String> invoiceMap = new HashMap<>();
			Timestamp stamp = invoice.getCreateTime();
			invoiceMap.put("create_time", sdf.format(stamp));
			invoiceMap.put("title", invoice.getTitle());
			invoiceMap.put("invoice_id", String.valueOf(invoice.getId()));
			stamp = invoice.getDate();
			invoiceMap.put("date", sdf.format(invoice.getDate()));
			invoiceMap.put("desc", invoice.getDesc());
			invoiceMap.put("amount", String.valueOf(invoice.getAmount()));
			String expenseName = expenseTypeService
					.getExpenseTypeNameById(invoice.getExpenseTypeId());
			if (expenseName == null || expenseName.isEmpty()) {
				expenseName = "未设置";
			}
			invoiceMap.put("expense_type_name", expenseName);
			invoiceMap.put("cost_center", invoice.getCostCenter());
			invoiceMap.put("invoice_code", invoice.getInvoiceCode());
			invoiceMap.put("identify_status",
					invoice.getIdentifyStatus() == 0 ? "识别中" : "已识别");
			String fileName = attachmentService.getAttachMentByInvoiceId(
					invoice.getId()).getImage();
			invoiceMap.put("file_name", fileName);
			retList.add(invoiceMap);
		}
		return retList;
	}

	@Override
	public Map<String, String> getUserInfo(String userId) {
		Map<String, String> retMap = new HashMap<>();
		String userName = userService.getUserNameByUserId(userId);
		long companyId = userService.getCompanyIdByUserId(userId);
		String companyName = companyService.getCompanyName(companyId);
		long deptId = userDeptService.getDeptIdByUserId(userId);
		String deptName = deptService.getDeptNameById(deptId);
		String costCenterCode = deptService.getCostCenterCodeById(deptId);
		String approvalName = "";
		List<String> userList = userService.getApprovalUserList(userId);
		for (String uId : userList) {
			String uName = userService.getUserNameByUserId(uId);
			approvalName += uName + ",";
		}
		if (!approvalName.isEmpty())
			approvalName = approvalName.substring(0, approvalName.length() - 1);
		retMap.put("user_name", userName);
		retMap.put("company_name", companyName);
		retMap.put("approval_name", approvalName);
		retMap.put("user_dept_name", deptName);
		retMap.put("cost_center_code", costCenterCode);
		logger.debug("retMap : {}", retMap.toString());
		return retMap;
	}

	@Override
	public boolean updateInvoiceInfo(Map<String, String> params) {
		return singleInvoiceService.updateInvoiceInfo(params);
	}

	@Override
	public boolean submitInvoiceAudit(String userId, String idList) {
		String[] ids = idList.split(",");
		Map<String, String> params = new HashMap<>();
		params.put("date", new Timestamp(System.currentTimeMillis()).toString());
		if (ids == null || ids.length == 0)
			return false;
		for (String invoiceId : ids) {
			invoiceService.submitUserInvoice(userId, Long.valueOf(invoiceId),
					params);
		}
		return true;
	}

	@Override
	public boolean deleteInvoice(long invoiceId) {
		return invoiceService.deleteInvoiceByInvoiceId(invoiceId);
	}

	@Override
	public List<Map<String, Object>> getWaitInvoiceList(String userId) {
		Map<String, List<Map<String, Object>>> retMap = invoiceService
				.getBillsList(userId);
		return retMap.get("processingList");
	}

	@Override
	public List<Map<String, Object>> getFinishInvoiceList(String userId) {
		Map<String, List<Map<String, Object>>> retMap = invoiceService
				.getBillsList(userId);
		return retMap.get("finishedList");
	}

	@Override
	public List<Map<String, Object>> getNeedAuditInvoiceList(String userId) {
		Map<String, List<Map<String, Object>>> retMap = invoiceService
				.getNeededAuditBillList(userId);
		return retMap.get("processingList");
	}

	@Override
	public List<Map<String, Object>> getFinishAuditInvoiceList(String userId) {
		Map<String, List<Map<String, Object>>> retMap = invoiceService
				.getNeededAuditBillList(userId);
		return retMap.get("finishedList");
	}

	@Override
	public List<Map<String, Object>> getInvoiceHistory(String userId) {
		List<Map<String, Object>> retList = null;
		Map<String, List<Map<String, Object>>> retMap = invoiceService
				.getBillsList(userId);
		if (retMap.get("processingList") != null
				&& retMap.get("processingList").size() > 0) {
			retList = retMap.get("processingList");
		}
		if (retMap.get("finishedList") != null
				&& retMap.get("finishedList").size() > 0) {
			if (retList == null) {
				retList = retMap.get("finishedList");
			} else {
				retList.addAll(retMap.get("finishedList"));
			}
		}
		if (retMap.get("uploadedList") != null
				&& retMap.get("uploadedList").size() > 0) {
			if (retList == null) {
				retList = retMap.get("uploadedList");
			} else {
				retList.addAll(retMap.get("uploadedList"));
			}
		}
		return retList;
	}

	@Override
	public boolean submitAuditResult(String userId, String invoiceList, int status) {
		if(invoiceList == null || invoiceList.isEmpty()) return false;
		boolean allSuccess = true;
		List<String> invoiceIdAndUserIdList = (List<String>)Arrays.asList(invoiceList.split(","));
		for(String invoiceInfo : invoiceIdAndUserIdList) {
			String[] infos = invoiceInfo.split("&");
			if(infos.length != 3) continue;
			String invoiceId = infos[0];
			String invoiceUserId = infos[1];
			String reasons = infos[2];
			Map<String, Object> retMap = invoiceService.excuteApproval(invoiceUserId, userId, invoiceId, status, reasons);
			if(retMap.get("success") == null || !(boolean)retMap.get("success")) {
				allSuccess = false;
			}
		}
		return allSuccess;
	}
}
