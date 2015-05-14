package com.wisdom.weixin.utils;

import java.io.Serializable;

public class SubmitAuditBillResultEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user_id;
	
	private String invoice_id;
	
	private String approval_id;
	
	private String approval_status;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



	public String getInvoice_id() {
		return invoice_id;
	}



	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}



	public String getApproval_id() {
		return approval_id;
	}



	public void setApproval_id(String approval_id) {
		this.approval_id = approval_id;
	}



	public String getApproval_status() {
		return approval_status;
	}



	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}

	public SubmitAuditBillResultEntity(String user_id, String invoice_id,
			String approval_id, String approval_status) {
		super();
		this.user_id = user_id;
		this.invoice_id = invoice_id;
		this.approval_id = approval_id;
		this.approval_status = approval_status;
	}

	@Override
	public String toString() {
		return "SubmitAuditBillResultEntity [user_id=" + user_id
				+ ", invoice_id=" + invoice_id + ", approval_id=" + approval_id
				+ ", approval_status=" + approval_status + "]";
	}

	public SubmitAuditBillResultEntity() {}
	
}
