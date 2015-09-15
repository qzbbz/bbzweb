package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Accounter entity. @author MyEclipse Persistence Tools
 */

public class CompanyBill implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long companyId;
	private Double amount;
	private String type;
	private String fileName;
	private String billDate;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public CompanyBill() {
	}

	/** minimal constructor */
	public CompanyBill(Long id) {
		this.id = id;
	}

	public CompanyBill(Long id, Long companyId, Double amount, String type,
			String fileName, String billDate, Timestamp createTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.amount = amount;
		this.type = type;
		this.fileName = fileName;
		this.billDate = billDate;
		this.createTime = createTime;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	@Override
	public String toString() {
		return "CompanyBill [id=" + id + ", companyId=" + companyId + ", amount=" + amount + ", type=" + type
				+ ", fileName=" + fileName + ", billDate=" + billDate + ", createTime=" + createTime + "]";
	}
	
}