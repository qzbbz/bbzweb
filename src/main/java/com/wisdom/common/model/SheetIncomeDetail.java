package com.wisdom.common.model;

import java.sql.Timestamp;
public class SheetIncomeDetail implements java.io.Serializable {

	private Long id;
	private Long companyId;
	private String dateTime;
	private String voucherType;
	private String voucherNo;
	private String attachmentNums;
	private String abstracts;
	private String subject;
	private String borrower;
	private String lender;
	private String touching;
	private String pchk;
	private String customer;
	private String project;
	public SheetIncomeDetail() {
		super();
	}
	public SheetIncomeDetail(Long id, Long companyId, String dateTime, String voucherType, String voucherNo,
			String attachmentNums, String abstracts, String subject, String borrower, String lender, String touching,
			String pchk, String customer, String project) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.dateTime = dateTime;
		this.voucherType = voucherType;
		this.voucherNo = voucherNo;
		this.attachmentNums = attachmentNums;
		this.abstracts = abstracts;
		this.subject = subject;
		this.borrower = borrower;
		this.lender = lender;
		this.touching = touching;
		this.pchk = pchk;
		this.customer = customer;
		this.project = project;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getAttachmentNums() {
		return attachmentNums;
	}
	public void setAttachmentNums(String attachmentNums) {
		this.attachmentNums = attachmentNums;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public String getLender() {
		return lender;
	}
	public void setLender(String lender) {
		this.lender = lender;
	}
	public String getTouching() {
		return touching;
	}
	public void setTouching(String touching) {
		this.touching = touching;
	}
	public String getPchk() {
		return pchk;
	}
	public void setPchk(String pchk) {
		this.pchk = pchk;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
}