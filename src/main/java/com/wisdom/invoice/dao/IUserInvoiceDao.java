package com.wisdom.invoice.dao;

import java.util.List;

import com.wisdom.common.model.InvoiceInfo;
import com.wisdom.common.model.UserInvoice;

public interface IUserInvoiceDao {

	public List<UserInvoice> getUserInvoiceByUserId(String userId);
	
	public List<UserInvoice> getUserInvoiceByUserIdAndStatusAndApprovalStatus(String userId, int status, int approvalStatus);
	
	public UserInvoice getUserInvoiceByInvoiceId(long invoiceId);
	public UserInvoice getUserInvoiceByUserIdAndInvoiceId(String userId,long invoiceId);
	
	public boolean addUserInvoice(UserInvoice userInvoice);
	
	public boolean deleteUserInvoice(UserInvoice userInvoice);
	
	public boolean updateUserInvoice(UserInvoice userInvoice);
	

	/*我的收件箱 begin*/
	public List<InvoiceInfo> getInvoiceInfoByCondition(String userId,String date,
					String submitter,Double amout,Integer expenseType,Integer page,Integer pageSize);
	/*我的收件箱 end*/
	
	public boolean updateUserInvoiceReason(long invoiceId, String reason);
	
	public List<UserInvoice> getUserInvoiceByApprovalIdAndStatus(String approvalId, int status);
	
	public UserInvoice getLatestUserInvoice(String userIds);

}
