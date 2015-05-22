package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * UserInvoice entity. @author MyEclipse Persistence Tools
 */

public class UserInvoice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private Long invoiceId;
	private Integer status;
	private Integer approvalStatus;
	private String approvalId;
	private String reasons;
	private Timestamp updateTime;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserInvoice() {
	}

	public UserInvoice(Long id, String userId, Long invoiceId, Integer status,
			Integer approvalStatus, String approvalId, String reasons,
			Timestamp updateTime, Timestamp createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.invoiceId = invoiceId;
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.approvalId = approvalId;
		this.reasons = reasons;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}

	// Property accessors

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

}