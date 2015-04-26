package com.wisdom.dispatch.dao;

import java.util.List;

import com.wisdom.common.model.Dispatcher;

public interface IDispatcherDao {

	public List<Dispatcher> getDispatcherByStatus(int status);
	
	public boolean addDispatcher(Dispatcher dispatcher);
	
	public boolean updateDispatcherByInvoiceId(Dispatcher dispatcher);
	
}
