package com.wisdom.dispatch.dao;

import java.util.List;

import com.wisdom.common.model.Dispatcher;

public interface IDispatcherDao {
	/**
	 * 查询所有
	 * @param status
	 * @return
	 */
	public List<Dispatcher> getDispatcherByStatus(int status);
	/**
	 * 查询返回根据创建时间倒序的前num个。
	 * @param status
	 * @param num
	 * @return
	 */
	public List<Dispatcher> getDispatcherByStatusAndNum(int status,int num);	
	/**
	 * 插入一条记录
	 * @param dispatcher
	 * @return
	 */
	public boolean addDispatcher(Dispatcher dispatcher);
	/**
	 * 根据invoiceid更新状态
	 * @param dispatcher
	 * @return
	 */
	public boolean updateDispatcherByInvoiceId(Dispatcher dispatcher);
	/**
	 * 
	 * @param invoiceId
	 * @return
	 */
	public Dispatcher getDispatcherByInvoiceId(long invoiceId);
}
