package com.wisdom.weixin.utils;

import java.io.Serializable;

public class SubmitBillEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String invoice_id;
	
	private String bill_expenseTypeId;
	
	private String bill_amount;
	
	public String getInvoice_id() {
		return invoice_id;
	}


	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}


	public String getBill_expenseTypeId() {
		return bill_expenseTypeId;
	}


	public void setBill_expenseTypeId(String bill_expenseTypeId) {
		this.bill_expenseTypeId = bill_expenseTypeId;
	}


	public String getBill_amount() {
		return bill_amount;
	}


	public void setBill_amount(String bill_amount) {
		this.bill_amount = bill_amount;
	}

	@Override
	public String toString() {
		return "SubmitBillEntity [invoice_id=" + invoice_id
				+ ", bill_expenseTypeId=" + bill_expenseTypeId
				+ ", bill_amount=" + bill_amount + "]";
	}


	public SubmitBillEntity() {}
	
}
