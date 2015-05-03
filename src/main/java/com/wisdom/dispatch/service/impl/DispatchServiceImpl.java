package com.wisdom.dispatch.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
	
	@Override
	public boolean addDispatcherRecord(String userId,String userName,long invoiceId,int status,int channalTypeID,
			String receiver,String openId,long objectTypeId) {
		Dispatcher notifyLog = new Dispatcher();
		notifyLog.setUserId(userId);
		notifyLog.setUserName(userName);
		notifyLog.setInvoiceId(invoiceId);
		notifyLog.setStatus(status); //0-初始状态 1-已经发送成功
		notifyLog.setChannelTypeId(channalTypeID);
		notifyLog.setReciever(receiver);
		notifyLog.setOpenId(openId);
		notifyLog.setObjectTypeId(objectTypeId); 
		
		return dispatcherDao.addDispatcher(notifyLog) ;
	}

	@Override
	public boolean getChannelType(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Dispatcher> getDispatcherByStatusAndNum(int status, int num) {
		return dispatcherDao.getDispatcherByStatusAndNum(status, num);
	}

	@Override
	public Dispatcher getDispatcherByInvoiceId(long invoiceId) {
		return dispatcherDao.getDispatcherByInvoiceId(invoiceId);
	}

	@Override
	public boolean updateDispatcherStatus(long invoiceId) {
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.setInvoiceId(invoiceId);
		dispatcher.setStatus(1);//已发送
		dispatcher.setUpdateTime(new Timestamp(new Date().getTime()));
		return dispatcherDao.updateDispatcherByInvoiceId(dispatcher);
	}

}
