package com.wisdom.dispatch.service;

import com.wisdom.common.model.Dispatcher;

public interface IDispatcherService {
	/**
	 * 增加一条消息分发记录
	 * @param dispatcher
	 * @return
	 */
	public boolean addDispatcherRecord(long invoiceId,int status,int channalTypeID,String receiver,long objectTypeId);
	
	/**
	 * 根据id获取channel type描述信息
	 * @param id
	 * @return
	 */
	public boolean getChannelType(Long id);
	
}
