package com.wisdom.common.model;

import java.sql.Timestamp;

public class InvoiceInfo implements java.io.Serializable{

	/**
	 * 数据库查表的到的发票表信息。多表联合的结果
	 */
	private static final long serialVersionUID = 1L;
	private Long invoiceId;
	private String invoiceCode;
	private String title;
	private Integer expenseTypeId;
	private Double amount;
	private String desc;
	private Timestamp date;
	private String costCenter;
	private Integer processStatus; 
	private Integer approvalStatus;
	private String approver;
	private String submitter;
	
	public InvoiceInfo(long invoiceId,String invoiceCode,String title,int expenseTypeId,double amount,String desc
			,Timestamp date,String costCenter,int processStatus,int approvalStatus,String approver,String submitter ){
		this.invoiceId=invoiceId;
		this.invoiceCode=invoiceCode;
		this.title=title;
		this.expenseTypeId=expenseTypeId;
		this.amount=amount;
		this.desc=desc;
		this.date=date;
		this.costCenter = costCenter;
		this.processStatus = processStatus;
		this.approvalStatus=approvalStatus;
		this.approver=approver;
		this.submitter=submitter;
		
	}
	
	
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getExpenseTypeId() {
		return expenseTypeId;
	}
	public void setExpenseTypeId(Integer expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public Integer getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}
	public Integer getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	
}
