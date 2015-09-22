package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Accounter entity. @author MyEclipse Persistence Tools
 */

public class CompanyPay implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long companyId;
	private Integer payStatus;
	private Double payAmount;
	private Integer serviceTime;
	private String orderNo;
	private Integer applyInvoice;
	private String mailAddress;
	private String contractFile;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public CompanyPay() {
	}

	/** minimal constructor */
	public CompanyPay(Long id) {
		this.id = id;
	}

	public CompanyPay(Long id, Long companyId, Integer payStatus, Double payAmount, Integer serviceTime,
			String orderNo, int applyInvoice, String mailAddress, String contractFile, Timestamp createTime) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.payStatus = payStatus;
		this.payAmount = payAmount;
		this.serviceTime = serviceTime;
		this.orderNo = orderNo;
		this.applyInvoice = applyInvoice;
		this.mailAddress = mailAddress;
		this.contractFile = contractFile;
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

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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
}