package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * Attachment entity. @author MyEclipse Persistence Tools
 */

public class Attachment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long invoiceId;
	private String image;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Attachment() {
	}

	/** minimal constructor */
	public Attachment(Long id, Long invoiceId, String image) {
		this.id = id;
		this.invoiceId = invoiceId;
		this.image = image;
	}

	/** full constructor */
	public Attachment(Long id, Long invoiceId, String image,
			Timestamp createTime) {
		this.id = id;
		this.invoiceId = invoiceId;
		this.image = image;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}