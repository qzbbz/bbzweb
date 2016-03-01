package com.weixin.dao;

import java.util.List;

import com.weixin.model.WeixinWaitAuditInvoiceModel;

public interface IWeixinDao {

	public List<WeixinWaitAuditInvoiceModel> getWeixinAuditInvoice(String openId, int status);
	
	public List<WeixinWaitAuditInvoiceModel> getWeixinNeededAuditInvoice(String openId);
	
	public List<WeixinWaitAuditInvoiceModel> getWeixinSubmitInvoiceByUserId(String userId);

}
