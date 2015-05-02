package com.wisdom.invoice.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisdom.common.model.Attachment;
import com.wisdom.common.model.Dispatcher;
import com.wisdom.common.model.Invoice;
import com.wisdom.common.model.InvoiceApproval;
import com.wisdom.common.model.UserInvoice;
import com.wisdom.dispatch.service.IDispatcherService;
import com.wisdom.invoice.dao.IAttachmentDao;
import com.wisdom.invoice.dao.IInvoiceApprovalDao;
import com.wisdom.invoice.dao.IInvoiceDao;
import com.wisdom.invoice.dao.IUserInvoice;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.user.service.IUserWeixinService;

import org.springframework.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service("invoiceService")
public class InvoiceServiceImpl implements IInvoiceService {
	private static final Log log = LogFactory.getLog(InvoiceServiceImpl.class);
	@Autowired
	private IDispatcherService dispatcherService;
	@Autowired
	private IUserWeixinService userService;
	@Autowired
	private IInvoiceDao invoiceDao;
	@Autowired
	private IAttachmentDao attachmentDao;
	@Autowired
	private IUserInvoice userInvoiceDao;
	@Autowired
	private IInvoiceApprovalDao invoiceApprovalDao;
	
	@Transactional
	@Override
	public Map<String,Object> createInvoiceProcess(String userId, String image,
			String channelTypeId,String objectTypeId) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("success", false);
		if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(image) || StringUtils.isEmpty(channelTypeId)){
			log.error("null pointor error");
			return retMap;
		}
		
		Invoice invoice = new Invoice();
		Long invoiceId = addInvoiceRecord(invoice);
		if(null == invoiceId || invoiceId.longValue() == 0){
			log.error("addInvoiceRecord failed");
			return retMap;
		}
		boolean blRet = addAttachMentRecord(invoiceId,image);
		if(!blRet){
			log.error("addAttachMentRecord error");
			return retMap;
		}
		
		blRet = addUserInvoiceRecord(invoiceId,userId,0);
		if(!blRet){
			log.error("addUserInvoiceRecord error! userId=" + userId + ",invoiceId:" + invoice.getId());
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
		
		//先生成一条审批记录
		blRet = addInvoiceApprovalRecord(invoiceId,userId,0);
		if(!blRet){
			log.error("add invoiceAppovalRecord failed");
			retMap.put("message", "");
			return retMap;
		}
		
		
		//生成一条dispatcher日志。
		blRet = dispatcherService.addDispatcherRecord(invoiceId,0,1,receiver,1);		//TODO 
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
	
	
	
	@Override
	@Transactional
	public Map<String, Object> excuteApproval(String userId,
			String approvalUserId, String invoiceId) {
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
		Invoice invoice = getSingleInvoiceInfo(longId);
		if(null == invoice || null == invoice.getAmount() || 0 == invoice.getAmount()){
			log.error("发票信息不存在或不完整!");
			return retMap;
		}
		
		//根据发票ID查询审批状态
		UserInvoice userInvoice = userInvoiceDao.getUserInvoiceByInvoiceId(longId);
		if(null == userInvoice){
			log.error("发票状态信息不存在");
			return retMap;
		}
		if(userInvoice.getStatus()==1){
			log.debug("发票已经审批通过!");
			retMap.put("success", true);
			return retMap;
		}
		
		//执行审批
		if(updateApprovalRecord(userId,longId,1)){
			log.error("更新审批结果失败");
			return retMap;
		}
		
		//是否需要上一级审批
		if(!ifNeedSuperApproval(userId,approvalUserId,invoice.getAmount())){
			//更改发票审批状态
			updateInvoiceApprovalStatus(userId,longId,1);
			retMap.put("success", true);
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
		boolean blRet = addInvoiceApprovalRecord(Long.parseLong(invoiceId),userId,0);
		if(!blRet){
			log.error("add invoiceAppovalRecord failed");
			retMap.put("message", "");
			return retMap;
		}
		
		//生成一条dispatcher日志。
		blRet = dispatcherService.addDispatcherRecord(Long.parseLong(invoiceId),0,1,receiver,1);		//TODO 
		if(!blRet){
			log.error("add dispatcher log error!" + "userId:" + userId + ",reciever:" + receiver + ",InvoiceId:" + invoice.getId());
			return retMap;
		}
		retMap.put("success", true);
		return retMap;
	}

	
	public boolean checkUserAuth(String userId,String appovalUser){
		//TODO
		return true;		
	}
	
	public String getApprovalUserList(String userId){
		
		return new String(userId);
	}
	
	public boolean ifNeedSuperApproval(String userId,String approvalId,double amount){
//		TODO
		return false;
	}
	
	public boolean updateInvoiceApprovalStatus(String userId,long invoiceId,int status){
		UserInvoice userInvoice = new UserInvoice();
		userInvoice.setInvoiceId(invoiceId);
		userInvoice.setStatus(status);
		userInvoice.setUserId(userId);
		userInvoice.setUpdateTime(new Timestamp(new Date().getTime()));
		return userInvoiceDao.updateUserInvoice(userInvoice);
	}
	
	public boolean updateApprovalRecord(String userId,long invoiceId,int status){
		InvoiceApproval invoiceApproval = new InvoiceApproval();
		invoiceApproval.setInvoiceId(invoiceId);
		invoiceApproval.setStatus(status);
		invoiceApproval.setUserId(userId);
		invoiceApproval.setUpdateTime(new Timestamp(new Date().getTime()));
		return invoiceApprovalDao.updateInvoiceApproval(invoiceApproval);
	}

	@Override
	public Long addInvoiceRecord(Invoice invoice) {
		return invoiceDao.addInvoiceAndGetKey(invoice);
	}

	@Override
	public boolean addAttachMentRecord(long id,String image) {
		Attachment imageRecord = new Attachment();
		imageRecord.setInvoiceId(id);
		imageRecord.setImage(image);
		imageRecord.setCreateTime(new Timestamp(new Date().getTime()));
		return attachmentDao.addAttatchment(imageRecord);
	}

	@Override
	public boolean addUserInvoiceRecord(long invoiceId,String userId,int status) {
		UserInvoice userInvoice = new UserInvoice();
		userInvoice.setInvoiceId(invoiceId);
		userInvoice.setStatus(status);
		userInvoice.setUserId(userId);
		userInvoice.setCreateTime(new Timestamp(new Date().getTime()));
		return userInvoiceDao.addUserInvoice(userInvoice);
	}

	@Override
	public boolean addInvoiceAppovalRecord(InvoiceApproval invoiceApproval) {
		return invoiceApprovalDao.addInvoiceApproval(invoiceApproval);
	}

	@Override
	public Invoice getSingleInvoiceInfo(Long invoiceId) {
		return invoiceDao.getInvoiceById(invoiceId);
	}



	@Override
	public boolean addInvoiceApprovalRecord(long invoiceId, String approvalId,
			int status) {
		InvoiceApproval invoiceApproval = new InvoiceApproval();
		invoiceApproval.setInvoiceId(invoiceId);
		invoiceApproval.setStatus(0);
		invoiceApproval.setUserId(approvalId);
		Timestamp time = new Timestamp(new Date().getTime());
		invoiceApproval.setCreateTime(time);
		invoiceApproval.setUpdateTime(time);
		return false;
	}
	
	

}