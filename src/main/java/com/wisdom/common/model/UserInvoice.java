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
	private Timestamp updateTime;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserInvoice() {
	}

	/** minimal constructor */
	public UserInvoice(Long id, String userId, Long invoiceId, Integer status,
			Timestamp updateTime) {
		this.id = id;
		this.userId = userId;
		this.invoiceId = invoiceId;
		this.status = status;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public UserInvoice(Long id, String userId, Long invoiceId, Integer status,
			Timestamp updateTime, Timestamp createTime) {
		this.id = id;
		this.userId = userId;
		this.invoiceId = invoiceId;
		this.status = status;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}

	// Property accessors

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

}