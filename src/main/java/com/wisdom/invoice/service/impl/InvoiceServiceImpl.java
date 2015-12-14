package com.wisdom.invoice.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisdom.common.model.Attachment;
import com.wisdom.common.model.Dispatcher;
import com.wisdom.common.model.Invoice;
import com.wisdom.common.model.InvoiceApproval;
import com.wisdom.common.model.TestInvoice;
import com.wisdom.common.model.TestInvoiceRecord;
import com.wisdom.common.model.UserInvoice;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.company.service.IExpenseTypeService;
import com.wisdom.dispatch.service.IDispatcherService;
import com.wisdom.invoice.dao.IInvoiceDao;
import com.wisdom.invoice.domain.ApprovalStatus;
import com.wisdom.invoice.domain.InvoiceInfoVo;
import com.wisdom.invoice.domain.InvoiceStatus;
import com.wisdom.invoice.domain.ProcessStatus;
import com.wisdom.invoice.service.IAttachmentService;
import com.wisdom.invoice.service.IInvoiceApprovalService;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.invoice.service.ISingleInvoiceService;
import com.wisdom.invoice.service.IUserInvoiceService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.utils.RedisSetting;

import net.sf.json.JSONArray;

import com.wisdom.web.api.impl.CompanyUserApiImpl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import org.springframework.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
@Service("invoiceService")
public class InvoiceServiceImpl implements IInvoiceService {
	private static final Log log = LogFactory.getLog(InvoiceServiceImpl.class);
	private static final Logger logger = LoggerFactory
			.getLogger(InvoiceServiceImpl.class);
	@Autowired
	private IDispatcherService dispatcherService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IInvoiceDao invoiceDao;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private IUserInvoiceService userInvoiceService;
	@Autowired
	private IInvoiceApprovalService invoiceApprovalService;
	@Autowired
	private IExpenseTypeService expenseTypeService;
	@Autowired
	private ISingleInvoiceService singleInvoiceService;
	@Autowired
	private ICompanyService companyService;

	
	
	@Transactional
	@Override
	public Map<String,Object> createInvoiceProcess(String userId, String image,
			String channelTypeId,String objectTypeId,Map<String,Object> params) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("success", false);
		log.debug("createInvoiceProcess");
		if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(image) || StringUtils.isEmpty(channelTypeId)){
			log.error("null pointor error");
			return retMap;
		}
		
		if(null == params || null == (Integer)params.get("expenseTypeId")){
			log.error("expenseTypeId not exsited");
			retMap.put("message", "lost expenseTypeId param!");
			return retMap;
		}
		if(null == params || null == (Double)params.get("amount")){
			log.error("amount not exsited");
			retMap.put("message", "lost amount param!");
			return retMap;
		}
		if(null == params || null == (String)params.get("costCenterCode")){
			log.error("costCenterCode not exsited");
			retMap.put("message", "lost costCenterCode param!");
			return retMap;
		}
		int expenseTypeId = (Integer)params.get("expenseTypeId");
		double amount = (Double)params.get("amount");
		String costCenterCode = (String)params.get("costCenterCode");
		Invoice invoice = new Invoice();
		invoice.setExpenseTypeId(expenseTypeId);
		invoice.setAmount(amount);
		invoice.setCostCenter(costCenterCode);
		invoice.setStatus(InvoiceStatus.SUBMITTED); //微信上传默认为草稿
		log.debug("double amount : " + amount);
		log.debug("params amount : " + params.get("amount"));
		
		log.debug("addInvoiceRevord");
		Long invoiceId = singleInvoiceService.addInvoiceRecord(invoice);
		if(null == invoiceId || invoiceId.longValue() == -1){
			log.error("addInvoiceRecord failed");
			return retMap;
		}
		//Put the data into the new table
		TestInvoice newInvoice = new TestInvoice();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date now=new Date();
		String billDate = format.format(now);
		long companyId = userService.getCompanyIdByUserId(userId);
		
		Path p = Paths.get(image);
		String fileName = p.getFileName().toString();
		newInvoice.setBillDate(billDate);
		newInvoice.setCompanyId(companyId);
		newInvoice.setFileName(fileName);
		newInvoice.setIsFixedAssets(0);
		newInvoice.setCostCenter(costCenterCode);
		long newInvoiceId = invoiceDao.addInvoice(newInvoice);
		log.error("Add new Invoice: line 144");
		String companyName = companyService.getCompanyName(companyId);
		publishUnrecognizedInvoive(newInvoiceId, companyId, fileName, companyName);
		log.error("Add new Invoice: line 147");		
		log.debug("addAttachMentRecord");
		boolean blRet = attachmentService.addAttachMentRecord(newInvoiceId,image);
		logger.debug("attachmentService image : " + image);
		if(!blRet){
			log.error("addAttachMentRecord error");
			return retMap;
		}
		
		//TODO 获取当前用户的审批信息。
		String receiver = new String("");
		receiver = getApprovalUserList(userId);
		if(StringUtils.isEmpty(receiver)){
			log.error("get approval user error");
			retMap.put("message", "获取审批人信息失败!");
			return retMap;
		}
		
		log.debug("addUserInvoiceRecord");
		blRet = userInvoiceService.addUserInvoiceRecord(newInvoiceId,userId,receiver,ProcessStatus.PROCESSING);
		if(!blRet){
			log.error("addUserInvoiceRecord error! userId=" + userId + ",invoiceId:" + invoice.getId());
			return retMap;
		}
		
		//先生成一条审批记录
		blRet = invoiceApprovalService.addInvoiceApprovalRecord(newInvoiceId,receiver,0);
		if(!blRet){
			log.error("add invoiceAppovalRecord failed");
			retMap.put("message", "");
			return retMap;
		}
		
		//获取用户openId
		String openId = "";
		openId = userService.getOpenIdByUserId(receiver);	//TODO 多个审批人场景还要处理一下。
		String userName = userService.getUserNameByUserId(userId); 
		log.debug("getUserNameByUserId:" + userName);
		//生成一条dispatcher日志。
		blRet = dispatcherService.addDispatcherRecord(userId,userName,newInvoiceId,0,0,receiver,openId,1);		//TODO 
		if(!blRet){
			log.error("add dispatcher log error!" + "userId:" + userId + ",reciever:" + receiver + ",InvoiceId:" + invoice.getId());
			return retMap;
		}
		//成功后put相关信息
		retMap.put("invoiceId", invoice.getId());
		retMap.put("receiver",receiver);
		retMap.put("success", true);
		retMap.put("message", "提交审批流程成功!");
		return retMap;
	}
	
	@Transactional
	@Override
	public Map<String,Object> createDraftInvoice(String userId, String image, String costCenterCode) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("success", false);
		logger.debug("createInvoiceProcess");
		if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(image)){
			log.error("null pointor error");
			return retMap;
		}
		Invoice invoice = new Invoice();
		invoice.setCostCenter(costCenterCode);
		invoice.setStatus(InvoiceStatus.DRAFT); //微信上传默认为草稿
		
		log.debug("addInvoiceRevord");
		Long invoiceId = singleInvoiceService.addInvoiceRecord(invoice);
		if(null == invoiceId || invoiceId.longValue() == -1){
			log.error("addInvoiceRecord failed");
			return retMap;
		}
		log.debug("addAttachMentRecord");
		boolean blRet = attachmentService.addAttachMentRecord(invoiceId,image);
		logger.debug("attachmentService image : " + image);
		if(!blRet){
			log.error("addAttachMentRecord error");
			return retMap;
		}
		
		//TODO 获取当前用户的审批信息。
		String receiver = new String("");
		receiver = getApprovalUserList(userId);
		if(StringUtils.isEmpty(receiver)){
			log.error("get approval user error");
			retMap.put("message", "获取审批人信息失败!");
			return retMap;
		}
		
		log.debug("addUserInvoiceRecord");
		blRet = userInvoiceService.addUserInvoiceRecord(invoiceId,userId,receiver,ProcessStatus.PROCESSING);
		if(!blRet){
			log.error("addUserInvoiceRecord error! userId=" + userId + ",invoiceId:" + invoice.getId());
			return retMap;
		}
		
		retMap.put("invoiceId", invoice.getId());
		retMap.put("receiver",receiver);
		retMap.put("success", true);
		retMap.put("message", "提交审批流程成功!");
		return retMap;
	}	
	
	@Override
	@Transactional
	public Map<String, Object> excuteApproval(String userId,
			String approvalUserId, String invoiceId,int approvalStatus, String reasons) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("success", false);
		if(StringUtils.isEmpty(invoiceId) || StringUtils.isEmpty(userId)||StringUtils.isEmpty(approvalUserId)){
			log.error("null pointor error");
			return retMap;
		}
		
		//TODO check authentication
		if(!checkUserAuth(userId,approvalUserId)){
			log.error("have no rights to do that!");
			retMap.put("message","您没有权限执行该操作！");
			return retMap;			
		}
		long longId = Long.parseLong(invoiceId);
		Invoice invoice = singleInvoiceService.getSingleInvoiceInfo(longId);
		if(null == invoice || null == invoice.getAmount()){// || 0 == invoice.getAmount()){
			log.error("发票信息不存在或不完整!");
			retMap.put("message","发票信息不完整或不存在！");
			return retMap;
		}
		
		//根据发票ID查询审批状态
		UserInvoice userInvoice = userInvoiceService.getUserInvoiceByInvoiceId(longId);
		if(null == userInvoice){
			log.error("发票状态信息不存在");
			retMap.put("message","发票信息不存在！");
			return retMap;
		}
		if(userInvoice.getStatus()==1){
			log.debug("发票已经审批通过!");
			retMap.put("success", true);
			retMap.put("message","发票已经审批通过！");
			return retMap;
		}
		
		//执行审批：拒绝时候，直接结束
		if(1 == approvalStatus && invoiceApprovalService.updateApprovalRecord(userId,longId,1,approvalStatus)){
			userInvoiceService.updateInvoiceApprovalStatus(userId,approvalUserId,longId,1,approvalStatus, reasons);
			log.error("审批成功，审批拒绝!");
			retMap.put("message","审批成功，审批拒绝！");
			retMap.put("success", true);
			return retMap;
		}
		
		//是否需要上一级审批
		if(!ifNeedSuperApproval(userId,approvalUserId,invoice.getAmount())){
			//更改发票审批状态
			userInvoiceService.updateInvoiceApprovalStatus(userId,approvalUserId,longId,1,approvalStatus, reasons);
			invoiceApprovalService.updateApprovalRecord(userId,longId,1,approvalStatus);
			retMap.put("success", true);
			retMap.put("message", "发票审批成功！");
			return retMap;
		}
		
		//TODO 获取当前用户的审批信息。
		String receiver = new String("");
		receiver = getApprovalUserList(approvalUserId);
		if(StringUtils.isEmpty(receiver)){
			log.error("get approval user error");
			retMap.put("message", "获取审批人信息失败!");
			return retMap;
		}
		
		//先生成一条审批记录
		boolean blRet = invoiceApprovalService.addInvoiceApprovalRecord(Long.parseLong(invoiceId),userId,0);
		if(!blRet){
			log.error("add invoiceAppovalRecord failed");
			retMap.put("message", "");
			return retMap;
		}
		
		//生成一条dispatcher日志。
		String openId = userService.getOpenIdByUserId(userId);
		String userName = userService.getUserNameByUserId(userId);
		blRet = dispatcherService.addDispatcherRecord(userId,userName,Long.parseLong(invoiceId),0,1,receiver,openId,1);		//TODO 
		if(!blRet){
			log.error("add dispatcher log error!" + "userId:" + userId + ",reciever:" + receiver + ",InvoiceId:" + invoice.getId());
			return retMap;
		}
		retMap.put("success", true);
		return retMap;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getBillsList(String userId) {
		Map<String, List<Map<String, Object>>> retMap=new HashMap<String, List<Map<String, Object>>>();
		retMap.put("uploadedList", null);
		retMap.put("processingList", null);
		retMap.put("finishedList", null);
		
		if(StringUtils.isEmpty(userId)){
			log.error("input param error");
			return retMap;		
		}
		
		List<UserInvoice> billList = userInvoiceService.getUserInvoiceByUserId(userId);
		if(null == billList){
			log.debug("no bill existed");
			return retMap;
		}
		log.debug("UserInvoiceList : " + billList.size());
		List<Map<String,Object>> processingList = new ArrayList<Map<String,Object>>(); 
		List<Map<String,Object>> finishedList = new ArrayList<Map<String,Object>>(); 
		List<Map<String,Object>> uploadedList = new ArrayList<Map<String,Object>>(); 
		
		try {
			for(UserInvoice invoice:billList) {
				Map<String,Object> billInfo = new HashMap<String,Object>();
				billInfo = getBillInfo(billInfo,invoice);
				if(1 == invoice.getStatus()){
					billInfo.put("bill_status", "1");
					finishedList.add(billInfo);
				}else{
					Dispatcher dispatch = dispatcherService.getDispatcherByInvoiceId(invoice.getInvoiceId());
					//if(null != dispatch && dispatch.getStatus() == 1){
					if(null != dispatch){
						billInfo.put("bill_status", "0");
//						billInfo.put("approval_id",dispatch.getReciever());
						processingList.add(billInfo);
					} else {
						uploadedList.add(billInfo);
					}
					/*}else if(null != dispatch && dispatch.getStatus() == 0){
						billInfo.put("bill_status", "0");
//						billInfo.put("approval_id",dispatch.getReciever());
						uploadedList.add(billInfo);
					}else{
						uploadedList.add(billInfo);
					}*/
				}
			}
			if(uploadedList.size()>0)
				retMap.put("uploadedList", uploadedList);
			if(processingList.size()>0)
				retMap.put("processingList", processingList);
			if(finishedList.size()>0)
				retMap.put("finishedList", finishedList);
		} catch (Exception e) {
			log.error("operation failed");
			e.printStackTrace();
		}
		return retMap;
	}
	
	public Map<String,Object> getBillInfo(Map<String,Object> map,UserInvoice userInvoice){
		if(userInvoice == null ){
			return map;
		}
	
		map.put("invoice_id", userInvoice.getInvoiceId());
		map.put("user_id", userInvoice.getUserId());
		map.put("processStatus",userInvoice.getStatus());
		map.put("reasons",userInvoice.getReasons() == null || userInvoice.getReasons().isEmpty() ? "无" : userInvoice.getReasons());
		map.put("approval_status", userInvoice.getApprovalStatus() == 0?true:false);
		map.put("approval_id", userInvoice.getApprovalId());
		String userName = userService.getUserNameByUserId(userInvoice.getUserId());
		map.put("user_name", userName);
		
		Invoice invoice =  singleInvoiceService.getSingleInvoiceInfo(userInvoice.getInvoiceId());
		if(null == invoice){
			log.debug("invoice not exsisted");
			map.clear();
			return map;
		}
		map.put("bill_title", invoice.getTitle());
		map.put("bill_commit_status", invoice.getStatus());
		map.put("bill_amount", invoice.getAmount());
		map.put("bill_desc", invoice.getDetailDesc() == null || invoice.getDetailDesc().isEmpty() ? "无" : invoice.getDetailDesc());
		map.put("bill_expenseTypeId", invoice.getExpenseTypeId());
		String exTypeName = expenseTypeService.getExpenseTypeNameById(invoice.getExpenseTypeId());
		map.put("bill_expenseTypeName", exTypeName == null || exTypeName.isEmpty() ? "未设置" : exTypeName);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Timestamp stamp = invoice.getDate();
		map.put("bill_date", sdf.format(stamp));
		stamp = userInvoice.getUpdateTime();
		map.put("update_time",sdf.format(stamp));
		if(!StringUtils.isEmpty((String)map.get("approval_id"))){
			String approvalName = userService.getUserNameByUserId((String)map.get("approval_id"));
			map.put("approval_name", approvalName);
		}
		
		Attachment attach = attachmentService.getAttachMentByInvoiceId(invoice.getId());
		if(null != attach){
			map.put("bill_img", "/files/company/" + attach.getImage());
		}

		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, List<Map<String, Object>>> getNeededAuditBillList(String approvalId){
		Map<String, List<Map<String, Object>>> retMap = new HashMap<String, List<Map<String, Object>>>();
		retMap.put("finishedList",null);
		retMap.put("processingList",null);
		logger.debug("getNeededAuditBillList approvalId : {}", approvalId);
		List<InvoiceApproval> invoiceApprovalList = invoiceApprovalService.getInvoiceApprovalListByUserId(approvalId);
		if(null == invoiceApprovalList){
			log.error("null invoiceApprovalList error:" + approvalId);
			return retMap;
		}
		logger.debug("invoiceApprovalList length : {}", invoiceApprovalList.size());
		
		List<Map<String,Object>> finishedList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> processingList = new ArrayList<Map<String,Object>>();
		for(InvoiceApproval invoiceApproval : invoiceApprovalList){
			logger.debug("invoiceApproval, invoice_id :{}", invoiceApproval.getInvoiceId());
			Map map = new HashMap<String,Object>();
			map.put("processStatus", invoiceApproval.getStatus());
			map.put("invoice_id", invoiceApproval.getInvoiceId());
			map.put("approval_status", invoiceApproval.getApprovalStatus());
			map.put("approval_id", approvalId);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Timestamp stamp = invoiceApproval.getUpdateTime();
			map.put("submit_time", sdf.format(stamp));
			UserInvoice userInvoice = userInvoiceService.getUserInvoiceByInvoiceId(invoiceApproval.getInvoiceId());
			if(null != userInvoice){
				map.put("user_id", userInvoice.getUserId());  
				stamp = userInvoice.getCreateTime();
				map.put("bill_date", sdf.format(stamp));
				String userName = userService.getUserNameByUserId(userInvoice.getUserId());
				map.put("user_name", userName);
				map.put("bill_status", userInvoice.getStatus());
				String approvalName = userService.getUserNameByUserId(userInvoice.getUserId());
				map.put("approval_name", approvalName);
				map.put("reasons", userInvoice.getReasons() == null || userInvoice.getReasons().isEmpty() ? "无" : userInvoice.getReasons());
			}		
			map.put("approval_id", approvalId);
			Invoice invoice =  singleInvoiceService.getSingleInvoiceInfo(invoiceApproval.getInvoiceId());
			if(null == invoice){
				logger.debug("invoice not exsisted, invoice_id : {}", invoiceApproval.getInvoiceId());
				//return map;
				continue;
			}
			map.put("bill_title", invoice.getTitle());
			map.put("bill_amount", invoice.getAmount());
			map.put("bill_expenseTypeId", invoice.getExpenseTypeId());
			map.put("desc", invoice.getDetailDesc());
			map.put("bill_expenseTypeName", expenseTypeService.getExpenseTypeNameById(invoice.getExpenseTypeId()));
			Attachment attach = attachmentService.getAttachMentByInvoiceId(invoice.getId());
			if(null != attach){
				map.put("bill_img", "/files/company/" + attach.getImage());
			}
			
			if(0 == invoiceApproval.getStatus()){
				processingList.add(map);
			}else{
				finishedList.add(map);
			}
			
		}
		if(finishedList.size() > 0) retMap.put("finishedList", finishedList);
		if(processingList.size() > 0) retMap.put("processingList", processingList);
		return retMap;
	}

	@Override
	public InvoiceInfoVo getInvoiceInfo(long invoiceId) {
//		Invoice invoice = getSingleInvoiceInfo(invoiceId);
		Invoice invoice = singleInvoiceService.getSingleInvoiceInfoByStatus(invoiceId,0);
		if(null == invoice){
			log.info("no invoice existed!invoiceId:" + invoiceId);
			return null;
		}
		InvoiceInfoVo vo = new InvoiceInfoVo();
		vo.setAmount(invoice.getAmount());
		vo.setTitle(invoice.getTitle());
		vo.setInvoiceId(invoiceId);
		vo.setCostCenter(invoice.getCostCenter());
		vo.setDate(invoice.getDate());
		vo.setDesc(invoice.getDesc());
		
		UserInvoice userInvoice = userInvoiceService.getUserInvoiceByInvoiceId(invoiceId);
		
		vo.setProcessStatus(userInvoice.getStatus());
		vo.setApprovalStatus(userInvoice.getApprovalStatus());
		vo.setApprover(userInvoice.getApprovalId());
		return vo;
	}
	
	@Override
	public InvoiceInfoVo getInvoiceInfoByCode(String invoiceCode) {
		Invoice invoice = singleInvoiceService.getSingleInvoiceInfoByCode(invoiceCode);
		if(null == invoice){
			log.info("no invoice existed!invoiceId:" + invoiceCode);
			return null;
		}
		InvoiceInfoVo vo = new InvoiceInfoVo();
		vo.setAmount(invoice.getAmount());
		vo.setTitle(invoice.getTitle());
		vo.setInvoiceId(invoice.getId());
		vo.setCostCenter(invoice.getCostCenter());
		vo.setDate(invoice.getDate());
		vo.setDesc(invoice.getDesc());
		vo.setInvoiceCode(invoiceCode);
		
		UserInvoice userInvoice = userInvoiceService.getUserInvoiceByInvoiceId(invoice.getId());
		
		vo.setProcessStatus(userInvoice.getStatus());
		vo.setApprovalStatus(userInvoice.getApprovalStatus());
		vo.setApprover(userInvoice.getApprovalId());
		return vo;
	}
	
	
	/**
	 * 批量提交处于草稿状态的发票
	 * @param userId
	 * @param invoiceId
	 * @return
	 */
	@Override
	@Transactional
	public Map submitUserInvoice(String userId,long invoiceId, Map<String, String> params){
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("success",false);
		
		UserInvoice userInvoice = userInvoiceService.getUserInvoiceByUserIdAndInvoiceId(userId, invoiceId);
		if(null == userInvoice){
			log.error("invoice not existed error, userId :" + userId + ",invoiceId : " + invoiceId);
			retMap.put("msg", "invoice not existed");
			return retMap;
		}
		
		//TODO 获取当前用户的审批信息。
		String receiver = new String("");
		receiver = getApprovalUserList(userId);
		if(StringUtils.isEmpty(receiver)){
			log.error("get approval error!");
			retMap.put("msg", "get approval error!");
			return retMap;
		}
		
		//先生成一条审批记录
		boolean blRet = invoiceApprovalService.addInvoiceApprovalRecord(invoiceId,receiver,0);
		if(!blRet){
			log.error("add invoiceAppovalRecord failed");
			retMap.put("message", "add approval record error!");
			return retMap;
		}
		
		//获取用户openId
		String openId = "";
		openId = userService.getOpenIdByUserId(receiver);	//TODO 多个审批人场景还要处理一下。
		String userName = userService.getUserNameByUserId(userId); 
		log.debug("getUserNameByUserId:" + userName);
		//生成一条dispatcher日志。
		blRet = dispatcherService.addDispatcherRecord(userId,userName,invoiceId,0,0,receiver,openId,1);		//TODO 
		if(!blRet){
			log.error("add dispatcher log error!" + "userId:" + userId + ",reciever:" + receiver + ",InvoiceId:" + invoiceId);
			return retMap;
		}
		
		/*更新状态*/
		if(params != null) {
			singleInvoiceService.updateInvoiceInfo(params);
		}
		singleInvoiceService.updateInvoiceStatus(invoiceId,InvoiceStatus.SUBMITTED);
		userInvoiceService.updateInvoiceApprovalStatus(userId, receiver, invoiceId, ProcessStatus.PROCESSING, ApprovalStatus.APPROVALED, "");		
		retMap.put("success", true);
		return retMap;
	}
	
	public boolean checkUserAuth(String userId,String appovalUser){
		//TODO
		return true;		
	}
	
	public String getApprovalUserList(String userId){
		List<String> userList = userService.getApprovalUserList( userId);
		if(null == userList || !(userList.size()>0)){
			return new String("93628512@qq.com"); //TODO TEST
		}
		StringBuilder str = new StringBuilder();
		for(String o:userList){
			str.append(o).append(";");
		}
		return str.toString().substring(0, str.length()-1);
	}
	
	public boolean ifNeedSuperApproval(String userId,String approvalId,double amount){
		
		return userService.ifNeedSuperApproval( userId,  approvalId, amount);
	}

	@Override
	public boolean deleteInvoiceByInvoiceId(long invoiceId) {
		return invoiceDao.deleteInvoiceByInvoiceId(invoiceId);
	}

	@Override
	public Invoice getInvoiceById(long id) {
		return invoiceDao.getInvoiceById(id);
	}

	@Override
	public boolean setIsFAOfInvoice(long invoiceId, boolean isFA, String itemId) {
		return invoiceDao.setIsFAOfInvoice(invoiceId, isFA, itemId);
	}

	@Transactional
	@Override
	public boolean addInvoiceArtifact(long invoiceId, List<Map<String, String>> content, String itemId) {
		//invoiceDao.deleteInvoiceArtifactByInvoiceId(invoiceId, itemId);
		for(Map<String, String>row: content){
			String type = row.get("description");
			double amount = Double.parseDouble(row.get("amount"));
			String supplierName = row.get("supplier");
			invoiceDao.addInvoiceArtifact(invoiceId, amount, type, supplierName, itemId);
		}
		return true;
	}

	@Override
	public long addInvoice(long companyId, String fileName, String billDate, Integer isFA) {
		TestInvoice invoice = new TestInvoice();
		invoice.setCompanyId(companyId);
		invoice.setFileName(fileName);
		invoice.setBillDate(billDate);
		invoice.setIsFixedAssets(isFA);
		invoice.setCostCenter(null);
		long invoiceId = invoiceDao.addInvoice(invoice);
		return invoiceId;
	}

	@Override
	public void publishUnrecognizedInvoive(long invoiceId, long companyId, String fileName, String companyName) {
		System.out.println("Start to publish");
		Map<String, String> exportedData = new HashMap<>();
		exportedData.put("invoice_id", Long.toString(invoiceId));
		exportedData.put("company_id", Long.toString(companyId));
		exportedData.put("path", fileName);
		exportedData.put("company", companyName);
		String exportDataStr = JSONArray.fromObject(exportedData).toString();
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(RedisSetting.MAX_IDLE);
		poolConfig.setMinIdle(RedisSetting.MIN_IDLE);
		poolConfig.setTestOnBorrow(RedisSetting.TEST_ON_BORROW);
		poolConfig.setNumTestsPerEvictionRun(RedisSetting.NUM_TESTS_PER_EVICTION_RUN);
		poolConfig.setTimeBetweenEvictionRunsMillis(RedisSetting.TIME_BETWEEN_EVICTION_RUNS_MILLIS);
		poolConfig.setMaxWaitMillis(RedisSetting.MAX_WAIT_MILLIS);
		//poolConfig.setBlockWhenExhausted(org.apache.commons.pool.impl.GenericObjectPool.WHEN_EXHAUSTED_FAIL);


		// Timeout is set larger to the deploy environment
		JedisPool jedisPool = new JedisPool(poolConfig,RedisSetting.ADDRESS, RedisSetting.PORT, 1000, RedisSetting.PASSWORD);
		
	   ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
	    newFixedThreadPool.submit(new Runnable() {

	        @Override
	        public void run() {

	                
	                Jedis jedis = jedisPool.getResource();
	                try {
	                   jedis.publish("UNRECOGNIZED_INVOICE", exportDataStr);
	                } catch (Exception e) {
	                   e.printStackTrace();
	                } finally {
	                   jedisPool.returnResource(jedis);
	                }
	            

	        }
	    });
		
	}

	@Override
	public boolean removeRedundantInvoiceArtifact(Timestamp toTime) {
		return invoiceDao.removeRedundantInvoiceArtifact(toTime); 		
	}

	@Override
	public List<TestInvoiceRecord> getAllInvoicesByCompanyId(long companyId) {
		return invoiceDao.getAllInvoicesByCompanyId(companyId);
	}

	@Override
	public boolean updateInvoiceStatus(long invoiceId, String status) {
		return invoiceDao.updateInoviceStatus(invoiceId, status);
	}

	@Override
	public List<TestInvoiceRecord> getInvoicesByDate(String date, long companyId) {
		return invoiceDao.getInvoicesByDate(date, companyId);
	}

}