package com.wisdom.invoice.dao;

import java.util.List;

import com.wisdom.common.model.UserInvoice;

public interface IUserInvoiceDao {

	public List<UserInvoice> getUserInvoiceByUserId(String userId);
	
	public UserInvoice getUserInvoiceByInvoiceId(long invoiceId);
	
	public boolean addUserInvoice(UserInvoice userInvoice);
	
	public boolean deleteUserInvoice(UserInvoice userInvoice);
	
	public boolean updateUserInvoice(UserInvoice userInvoice);
	
}
