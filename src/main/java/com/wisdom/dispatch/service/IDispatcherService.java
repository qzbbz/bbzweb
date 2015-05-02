package com.wisdom.dispatch.service;

import java.util.List;

import com.wisdom.common.model.Dispatcher;

public interface IDispatcherService {
	/**
	 * 增加一条消息分发记录
	 * @param dispatcher
	 * @return
	 */
	public boolean addDispatcherRecord(String userId,String userName,long invoiceId,int status,int channalTypeID,String receiver,String openId,long objectTypeId);
	
	/**
	 * 根据id获取channel type描述信息
	 * @param id
	 * @return
	 */
	public boolean getChannelType(Long id);
	
	public List<Dispatcher> getDispatcherByStatusAndNum(int status,int num);
	
	public Dispatcher getDispatcherByInvoiceId(long id);
	
	public boolean updateDispatcherStatus(long invoiceId);
}
