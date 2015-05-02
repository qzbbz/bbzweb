package com.wisdom.dispatch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Dispatcher;
import com.wisdom.dispatch.dao.IDispatcherDao;
import com.wisdom.dispatch.service.IDispatcherService;
import com.wisdom.common.utils.JavaMail;

@Service("dispatcherService")
public class DispatchServiceImpl implements IDispatcherService {
	
	@Autowired
	private IDispatcherDao dispatcherDao;
	@Autowired
	private JavaMail javaMail;
	
	@Override
	public boolean addDispatcherRecord(long invoiceId,int status,int channalTypeID,String receiver,long objectTypeId) {
		Dispatcher notifyLog = new Dispatcher();
		notifyLog.setInvoiceId(invoiceId);
		notifyLog.setStatus(status); //0-初始状态 1-已经发送成功
		notifyLog.setChannelTypeId(channalTypeID);
		notifyLog.setReciever(receiver);
		notifyLog.setObjectTypeId(objectTypeId); 
		return dispatcherDao.addDispatcher(notifyLog) ;
	}

	@Override
	public boolean getChannelType(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
