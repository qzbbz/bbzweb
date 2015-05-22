package com.wisdom.invoice.service;

import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Attachment;
import com.wisdom.common.model.Invoice;
import com.wisdom.invoice.domain.InvoiceInfoVo;

public interface IInvoiceService {
	public InvoiceInfoVo getInvoiceInfo(long invoiceId);
	
	public InvoiceInfoVo getInvoiceInfoByCode(String invoiceCode);
	
	/**
	 * 根据userId查询该用户所有发票审批信息
	 * @param userId
	 * @return
	 */
	public Map<String,List<Map<String,Object>>> getBillsList(String userId);
	
	
	/**
	 * 创建一个新的发票处理记录 for weixin
	 * @param userId
	 * @param image
	 * @param channelType 0-所有终端   
	 * @param objectTypeId 1-发票报销
	 * @return
	 */
	public Map<String,Object> createInvoiceProcess(String userId,String image,String channelType,
			String objectTypeId,Map<String,Object> params);
	
	public Map<String,Object> createDraftInvoice(String userId, String image, String costCenterCode);
	
	/**
	 * 执行审批流程 for weixin
	 * @param userId
	 * @param approvalUserId
	 * @param incoiceId
	 * @param approavalStatus
	 * @return 
	 */
	public Map<String,Object> excuteApproval(String userId,String approvalUerId,String invoiceId,int approvalStatus, String reasons);
	
	public Map<String, List<Map<String, Object>>> getNeededAuditBillList(String userId);
	/**
	 * 将某个订单从草稿提交审批
	 * @param userId
	 * @param invoiceId
	 * @return
	 */
	public Map submitUserInvoice(String userId,long invoiceId, Map<String, String> params);
	
	public boolean deleteInvoiceByInvoiceId(long invoiceId);
}
