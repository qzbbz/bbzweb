package com.wisdom.accounter.dao;

import java.util.List;
import java.util.Map;

import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.CustomerManagement;
import com.wisdom.common.model.CustomerTaoBao;

public interface IAccounterDao {

	public Accounter getAccounterByUserId(String userId);
	
	public List<Accounter> getAccounterByCompanyId(long companyId);
	
	public List<Accounter> getAllAccounter();
	
	public List<Accounter> getAllAccounterByCondition(String province, String city, String area,
			String industry, String career);
	
	public boolean addAccounter(Accounter accounter);
	
	public boolean deleteAccounter(Accounter accounter);
	
	public boolean updateAccounter(Accounter accounter);
	
	public boolean isAccounterExistByUserId(String userId);
	
	public List<CustomerManagement> getAllCustomer(String accounterId);

	public List<Map<String, Object>> getAllCompanyExpense(String userId, int start, int length);

	public int getAllCompanyExpenseRecordTotal(String userId);

	public List<Map<String, Object>> getAllCompanyExpenseDataTableByCondition(String userId,
			Map<String, String> conditionMap, int start, int length);

	public int getAllCompanyExpenseByConditionRecordTotal(String userId, Map<String, String> conditions);
	
	public boolean addCustomerComment(CustomerTaoBao ctb);
	
	public int getCustomerTaoBaoCountByMonth(long companyId, int type);
}
