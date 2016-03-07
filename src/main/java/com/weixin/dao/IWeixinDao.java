package com.weixin.dao;

import java.util.List;

import com.weixin.model.WeixinWaitAuditInvoiceModel;
import com.weixin.model.WeixinWaitWorkGoingOutModel;

public interface IWeixinDao {

	public List<WeixinWaitAuditInvoiceModel> getWeixinAuditInvoice(String openId, int status);
	
	public List<WeixinWaitAuditInvoiceModel> getWeixinNeededAuditInvoice(String openId);
	
	public List<WeixinWaitAuditInvoiceModel> getWeixinSubmitInvoiceByUserId(String userId);
	
	public List<WeixinWaitWorkGoingOutModel> getWeixinWaitWorkGoingOut(String openId, int status);
	
	public List<WeixinWaitWorkGoingOutModel> getAuditingWorkGoingOut(String openId, int status);
	
	public List<WeixinWaitWorkGoingOutModel> getWeixinAuditWorkGoingOut(String openId, int status);
	
	public  List<WeixinWaitWorkGoingOutModel> getWeixinAuditWorkGoingOutByUserId(String userId, int status);
	
	public boolean updateUserWorkGoingOut(String workId, String approval_status, String reasons);
	
}
