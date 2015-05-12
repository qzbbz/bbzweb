package com.wisdom.invoice.service;

import com.wisdom.common.model.Attachment;

public interface IAttachmentService {
	public boolean addAttachMentRecord(long id,String image);
	/**
	 * 根据发票ID获取 发票图片
	 * @param invoiceId
	 * @return
	 */
	public Attachment getAttachMentByInvoiceId(long invoiceId);
}
