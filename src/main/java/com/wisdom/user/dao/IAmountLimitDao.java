package com.wisdom.user.dao;

import com.wisdom.common.model.AmountLimit;

public interface IAmountLimitDao {

	public boolean addAmountLimit(AmountLimit amountLimit);
	
	public boolean deleteAmountLimit(AmountLimit amountLimit);
	
	public boolean updateAmountLimit(AmountLimit amountLimit);
	
	public AmountLimit getAmountLimit(AmountLimit amountLimit);
	
}
