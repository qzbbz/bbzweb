package com.wisdom.invoice.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Attachment;
import com.wisdom.invoice.dao.IAttachmentDao;
import com.wisdom.invoice.service.IAttachmentService;

@Service("attachmentService")
public class AttachmentServiceImpl implements IAttachmentService{
	@Autowired
	private IAttachmentDao attachmentDao;
	
	
	@Override
	public boolean addAttachMentRecord(long id,String image) {
		Attachment imageRecord = new Attachment();
		imageRecord.setInvoiceId(id);
		imageRecord.setImage(image);
		imageRecord.setCreateTime(new Timestamp(new Date().getTime()));
		return attachmentDao.addAttatchment(imageRecord);
	}
	

	@Override
	public Attachment getAttachMentByInvoiceId(long invoiceId){
		return attachmentDao.getAttatchmentByInvoiceId(invoiceId);
	}
}
