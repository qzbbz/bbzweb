package com.wisdom.invoice.dao;

import com.wisdom.common.model.UserInvoice;

public interface IUserInvoice {

public UserInvoice getUserInvoiceByUserId(String userId);
	
	public UserInvoice getUserInvoiceByInvoiceId(long invoiceId);
	
	public boolean addUserInvoice(UserInvoice userInvoice);
	
	public boolean deleteUserInvoice(UserInvoice userInvoice);
	
	public boolean updateUserInvoice(UserInvoice userInvoice);
	
}
