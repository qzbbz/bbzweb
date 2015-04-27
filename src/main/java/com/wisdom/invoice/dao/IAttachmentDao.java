package com.wisdom.invoice.dao;

import com.wisdom.common.model.Attachment;

public interface IAttachmentDao {

	public Attachment getAttatchmentByInvoiceId(long invoiceId);
	
	public boolean addAttatchment(Attachment attatchment);
	
	public boolean deleteAttatchment(Attachment attatchment);
	
}
