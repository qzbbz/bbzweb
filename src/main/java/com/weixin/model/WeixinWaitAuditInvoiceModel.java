package com.weixin.model;

import java.sql.Timestamp;

public class WeixinWaitAuditInvoiceModel {

	private Timestamp create_time;
	
	private String file_name;
	
	private Long invoice_id;
	
	private Double amount;
	
	private String type;
	
	private String user_name;
	
	private Integer approval_status;
	
	private String reasons;
	
	private String user_id;
	
	public WeixinWaitAuditInvoiceModel(){}

	public WeixinWaitAuditInvoiceModel(Timestamp create_time, String file_name, Long invoice_id, Double amount,
			String type, String user_name, Integer approval_status, String reasons, String user_id) {
		super();
		this.create_time = create_time;
		this.file_name = file_name;
		this.invoice_id = invoice_id;
		this.amount = amount;
		this.type = type;
		this.user_name = user_name;
		this.approval_status = approval_status;
		this.reasons = reasons;
		this.user_id = user_id;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Long getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(Long invoice_id) {
		this.invoice_id = invoice_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(Integer approval_status) {
		this.approval_status = approval_status;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "WeixinWaitAuditInvoiceModel [create_time=" + create_time + ", file_name=" + file_name + ", invoice_id="
				+ invoice_id + ", amount=" + amount + ", type=" + type + ", user_name=" + user_name
				+ ", approval_status=" + approval_status + ", reasons=" + reasons + ", user_id=" + user_id + "]";
	}
		
}
