package com.wisdom.invoice.service;

import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Attachment;
import com.wisdom.common.model.Invoice;
import com.wisdom.common.model.InvoiceApproval;
import com.wisdom.common.model.UserInvoice;

public interface IInvoiceService {
	/**
	 * 根据userId查询该用户所有发票审批信息
	 * @param userId
	 * @return
	 */
	public Map<String,List<Map<String,Object>>> getBillsList(String userId);
	
	
	/**增加一条发票流水记录
	 * */
	public Long addInvoiceRecord(Invoice invoice);
	/**
	 * 增加一条发票图片记录
	 * @return
	 */
	public boolean addAttachMentRecord(long id,String image);
	
	public Attachment getAttachMentByInvoiceId(long invoiceId);
	
	/**增加一条发票和用户关系映射记录
	 * @param 
	 */
	public boolean addUserInvoiceRecord(long invoiceId,String userId,int status);
	
	/**
	 * 增加一条审批记录
	 * @param invoiceApproval
	 * @return
	 */
	public boolean addInvoiceAppovalRecord(InvoiceApproval invoiceApproval);
	
	/**
	 * 创建一个新的发票处理记录
	 * @param userId
	 * @param image
	 * @param channelType 0-所有终端  
	 * @param objectTypeId 1-发票报销
	 * @return
	 */
	public Map<String,Object> createInvoiceProcess(String userId,String image,String channelType,
			String objectTypeId);
	
	/**
	 * 执行审批
	 */
	public Map<String,Object> excuteApproval(String userId,String approvalUerId,String invoiceId,int approvalStatus);
	
	/**
	 * 
	 */
	public Invoice getSingleInvoiceInfo(Long invoiceId);
	
	public boolean addInvoiceApprovalRecord(long invoiceId,String approvalId,int status);
	
	
	

}
