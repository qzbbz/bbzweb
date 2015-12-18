package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Accounter entity. @author MyEclipse Persistence Tools
 */

public class CompanyPayHistory implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long companyId;
	private Double payAmount;
	private Integer serviceTime;
	private String orderNo;
	private Integer applyInvoice;
	private String mailAddress;
	private String contractFile;
	private Timestamp createdTime;
	private Integer payStatus;

	// Constructors

	/** default constructor */
	public CompanyPayHistory() {
	}

	/** minimal constructor */
	public CompanyPayHistory(Long id) {
		this.id = id;
	}

	public CompanyPayHistory(Long id, Long companyId, Double payAmount, Integer serviceTime,
			String orderNo, int applyInvoice, String mailAddress, String contractFile, Timestamp createdTime, Integer payStatus) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.payAmount = payAmount;
		this.serviceTime = serviceTime;
		this.orderNo = orderNo;
		this.applyInvoice = applyInvoice;
		this.mailAddress = mailAddress;
		this.contractFile = contractFile;
		this.payStatus = payStatus;
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

	public String getContractFile() {
		return contractFile;
	}

	public void setContractFile(String contractFile) {
		this.contractFile = contractFile;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public Integer getApplyInvoice() {
		return applyInvoice;
	}

	public void setApplyInvoice(Integer applyInvoice) {
		this.applyInvoice = applyInvoice;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	
	
}